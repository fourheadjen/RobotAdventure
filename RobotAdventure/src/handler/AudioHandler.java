package handler;

import java.applet.AudioClip;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.Port;

import constants.RobotAudioLoader;

public class AudioHandler {

	private static AudioClip[] gameSounds;
	private static int numberOfSoundClips;
	
	public static boolean muted;
	
	
	
	public AudioHandler()
	{
		muted = false;
		numberOfSoundClips = RobotAudioLoader.class.getFields().length;
		gameSounds = new AudioClip[numberOfSoundClips];
		gameSounds[SOUND.MENU_MUSIC.ordinal()] = RobotAudioLoader.menuMusic;
		gameSounds[SOUND.ROBOT_LASER.ordinal()] = RobotAudioLoader.laserBlast;
		gameSounds[SOUND.EXIT.ordinal()] = RobotAudioLoader.exit;
		gameSounds[SOUND.MENU_SELECT.ordinal()] = RobotAudioLoader.select;
		
		info();
		//masterOutputLine = getMasterOutputLine();
		//setVolume(0.0f);
	}
		
	public static void playSound(SOUND sound)
	{
		if(sound.ordinal() >= gameSounds.length || muted)
			return;
		
		gameSounds[sound.ordinal()].play();
	}
	
	public static void loopSound(SOUND sound)
	{
		if(sound.ordinal() >= gameSounds.length || muted)
			return;
		
		gameSounds[sound.ordinal()].loop();
	}
	
	public static void stopSound(SOUND sound)
	{
		if(sound.ordinal() >= gameSounds.length)
			return;
		
		gameSounds[sound.ordinal()].stop();
	}
	
	public static void stopAllSound()
	{
		for(int i = 0; i < gameSounds.length; i++)
		{
			gameSounds[i].stop();
		}
	}
	
	public enum SOUND
	{
		MENU_MUSIC,
		ROBOT_LASER,
		EXIT,
		MENU_SELECT,
		POWERUP_PICKUP
	}
	
	/*
	 * Volume Control stuff i found online
	 */
	/**
	 * 
	 * @param value (a number between 0 and 1)
	 */
	
	public void info()
	{try {
		Mixer.Info[] infos = AudioSystem.getMixerInfo(); 
        for (Mixer.Info info: infos) 
        { 
            Mixer mixer = AudioSystem.getMixer(info); 
            if (mixer.isLineSupported(Port.Info.SPEAKER)) 
            { 
                Port port;
				
					port = (Port)mixer.getLine(Port.Info.SPEAKER);
				
                port.open(); 
                if (port.isControlSupported(FloatControl.Type.VOLUME)) 
                { 
                    FloatControl volume = (FloatControl)port.getControl(FloatControl.Type.VOLUME); 
                    System.out.println(info); 
                    System.out.println("- " + Port.Info.SPEAKER); 
                    System.out.println("  - " + volume); 
                    volume.setValue(0.5f);
                    System.out.println(info); 
                    System.out.println("- " + Port.Info.SPEAKER); 
                    System.out.println("  - " + volume); 
                    
                } 
                port.close(); 
            } 
        } 
		} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	}
	
	private static Line masterOutputLine;
	
	public static void setVolume(float value)
	{
		if(value < 0 || value > 1)
			return;
		Line line = masterOutputLine;
		if(line == null) return;
		boolean opened = open(line) || line instanceof Clip;
		
		try
		{
			FloatControl control = getVolumeControl(line);
			if(control == null)
				return;
			
			control.setValue(value);
		}finally
		{
			if(opened)line.close();
		}
	}
	
	private static boolean open(Line line)
	{
		if(line.isOpen())return false;
		try
		{
			line.open();
		}catch(LineUnavailableException ex)
		{
			return false;
		}
		
		return true;
		
	}
	
	private static FloatControl getVolumeControl(Line line)
	{
		if(!line.isOpen()) return null;
		return (FloatControl) findControl(FloatControl.Type.VOLUME, line.getControls());
	}
	
	private static Control findControl(Type type, Control... controls)
	{
		if(controls == null || controls.length == 0) return null;
		for(Control control : controls)
		{
			if(control.getType().equals(type)) return control;
			if(control instanceof CompoundControl)
			{
				CompoundControl compoundControl = (CompoundControl) control;
				Control member = findControl(type,compoundControl.getMemberControls());
				if(member != null) return member;
			}
		}
		return null;
	}
	
	private static Line getMasterOutputLine() { 

		for (Mixer mixer : getMixers()) { 
			for (Line line : getAvailableOutputLines(mixer)) {
				System.out.println("line Info: " + line.getLineInfo().toString());
				if (line.getLineInfo().toString().contains("SPEAKER")) 
				{
					System.out.println("FOUND A Clip");
					return line; 
				}
			} 
		} 
		return null; 
	} 
	
	private static List<Mixer> getMixers()
	{
		Info[] infos = AudioSystem.getMixerInfo();
		List<Mixer> mixers = new ArrayList<Mixer>(infos.length);
		for(Info info : infos)
		{
			Mixer mixer = AudioSystem.getMixer(info);
			mixers.add(mixer);
		}
		return mixers;
		
	}
	
	private static List<Line> getAvailableOutputLines(Mixer mixer)
	{
		return getAvailableLines(mixer,mixer.getSourceLineInfo());
	}
	
	private static List<Line> getAvailableLines(Mixer mixer, Line.Info[] lineInfos)
	{
		List<Line> lines = new ArrayList<Line>(lineInfos.length);
		for(Line.Info lineInfo : lineInfos)
		{
			Line line;
			line = getLineIfAvailable(mixer,lineInfo);
			if(line != null) lines.add(line);
		}
		return lines;
	}
	
	private static Line getLineIfAvailable(Mixer mixer, Line.Info lineInfo)
	{
		try
		{
			return mixer.getLine(lineInfo);
		}catch(LineUnavailableException ex)
		{
			return null;
		}
	}
	
	
}
