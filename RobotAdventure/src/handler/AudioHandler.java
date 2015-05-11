package handler;

import java.applet.AudioClip;

import constants.RobotAudioLoader;
import utilities.Utility;

public class AudioHandler {

	private static AudioClip[] gameSounds;
	
	private static int numberOfSoundClips;
	
	private AudioHandler(){}
	
	public static void playSound(int soundClipIndex)
	{
		if(soundClipIndex >= gameSounds.length)
			return;
		
		gameSounds[soundClipIndex].play();
	}
	
	public static void loopSound(int soundClipIndex)
	{
		if(soundClipIndex >= gameSounds.length)
			return;
		
		gameSounds[soundClipIndex].loop();
	}
	
	public static void stopSound(int soundClipIndex)
	{
		if(soundClipIndex >= gameSounds.length)
			return;
		
		gameSounds[soundClipIndex].stop();
	}
	
	public static void stopAllSound()
	{
		for(int i = 0; i < gameSounds.length; i++)
		{
			gameSounds[i].stop();
		}
	}
	
	static
	{
		numberOfSoundClips = RobotAudioLoader.class.getFields().length;
		gameSounds = new AudioClip[numberOfSoundClips];
		gameSounds[0] = RobotAudioLoader.menuMusic;
		System.out.println("NUM OF CLIPS: " + numberOfSoundClips);
		
	}
	
	public enum SOUND
	{
		MENU_MUSIC,
		ROBOT_LASER,
		POWERUP_PICKUP,
		MENU_SELECT
	}
}
