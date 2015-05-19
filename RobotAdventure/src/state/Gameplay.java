package state;

import game.RobotCanvas;
import handler.AudioHandler;

import java.awt.Color;
import java.awt.Graphics;

import utilities.RobotFont;

public class Gameplay extends GameState {

	public Gameplay(STATE stateID,GameStateManager ref) {
		super(stateID,ref);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, RobotCanvas.BUFFER_WIDTH, RobotCanvas.BUFFER_HEIGHT);
		RobotFont.drawString("GamePlay stuff goes here!", g, 100, 100);
	}

}
