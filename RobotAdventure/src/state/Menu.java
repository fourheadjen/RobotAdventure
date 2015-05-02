package state;

import game.RobotFrame;

import java.awt.Color;
import java.awt.Graphics;

import utilities.RobotFont;

public class Menu extends GameState {

	RobotFont f;
	
	public Menu(STATE stateID) {
		super(stateID);
		f = new RobotFont();
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RobotFrame.GAME_WIDTH, RobotFrame.GAME_HEIGHT);
		f.drawString("hello", g, 100, 100,2,2);
	}
}
