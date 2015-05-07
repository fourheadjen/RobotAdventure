package entity;

import game.RobotCanvas;

import java.awt.Graphics;

public class Robot extends PhysicsSprite {

	public Robot(int x, int y, int width, int height) {
		super(x, y, width, height, 0, null, null, RobotCanvas.playerWeight, RobotCanvas.playerDragC);
	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public void tick() {

	}

}
