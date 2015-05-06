package state;

import game.RobotCanvas;
import game.RobotFrame;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Toolkit;

import utilities.RobotFont;

public class Menu extends GameState {
	
	public Menu(STATE stateID) {
		super(stateID);
	}

	@Override
	public void tick() {
		score++;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RobotCanvas.BUFFER_WIDTH, RobotCanvas.BUFFER_HEIGHT);
		//RobotFont.drawString("the quick brown fox jumped over the lazy dog. 0123456789!aaaaaaa", g, 0, 100);
		//RobotFont.drawString("\"I, you\" > me ? #something 'waste' / 25", g, 10, 200);
		//RobotFont.drawString("[" + score + "]",g,10,300,2,2);
		//RobotFont.drawStringOverlapped("{" + score + "}", g, 10, 400,2,2);
		//RobotFont.drawStringReversed("reversed: racecar", g, 200, 400);
		//RobotFont.drawStringOverlapped("robot adventure", g, 300, 400, 2, 3, 1);
		RobotFont.drawMultiLineString("Robot Adventure\nBy:\nJonathan Collins\nSkylar Donlevy\nJennifer Vu", g, RobotCanvas.X_CENTERED_TEXT, RobotCanvas.Y_BOTTOM_TEXT,RobotFont.CENTER);
		//RobotFont.drawCirclularString("Robot Adventure", g, 500, 300, 2, 2,8,8);
		
	}
	private int score;
}
