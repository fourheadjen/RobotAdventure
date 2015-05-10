package constants;

import java.awt.Image;

import utilities.Utility;

public class RobotImageLoader {

	private static final String button = "textures/button/";
	
	private RobotImageLoader(){}
	
	/*
	 * Button Images
	 */
	public static final Image START_GAME_IMAGE = Utility.loadImage(button + "start_button.png");
	public static final Image HOW_TO_PLAY_IMAGE = Utility.loadImage(button + "how_to_play_button.png");
	public static final Image SETTINGS_IMAGE = Utility.loadImage(button + "settings_button.png");
	public static final Image EXIT_IMAGE = Utility.loadImage(button + "quit_button.png");
	public static final Image BACK_IMAGE = Utility.loadImage(button + "back_button.png");
}
