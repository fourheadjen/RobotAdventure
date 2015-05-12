package handler;

import java.applet.AudioClip;
import java.awt.Toolkit;

import constants.RobotAudioLoader;
import utilities.Utility;

public class AudioHandler {

	private static AudioClip[] gameSounds;
	private static int numberOfSoundClips;
	
	public static boolean muted;
	
	public AudioHandler()
	{
		muted = true;
		numberOfSoundClips = RobotAudioLoader.class.getFields().length;
		gameSounds = new AudioClip[numberOfSoundClips];
		gameSounds[SOUND.MENU_MUSIC.ordinal()] = RobotAudioLoader.menuMusic;
		gameSounds[SOUND.ROBOT_LASER.ordinal()] = RobotAudioLoader.laserBlast;
		gameSounds[SOUND.EXIT.ordinal()] = RobotAudioLoader.exit;
		gameSounds[SOUND.MENU_SELECT.ordinal()] = RobotAudioLoader.select;
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
}
