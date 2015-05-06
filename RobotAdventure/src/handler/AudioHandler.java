package handler;

import java.applet.AudioClip;

import utilities.Utility;

public class AudioHandler {

	private static AudioClip[] gameSounds;
	
	private static int numberOfSoundClips = 1;
	
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
	
	public static void stopAllSound(int soundClipIndex)
	{
		if(soundClipIndex >= gameSounds.length)
			return;
		
		for(int i = 0; i < gameSounds.length; i++)
		{
			gameSounds[i].stop();
		}
	}
	
	static
	{
		gameSounds = new AudioClip[numberOfSoundClips];
		gameSounds[0] = Utility.loadAudio("audio/music.wav");
	}
}
