package state;

import java.awt.Graphics;

import utilities.RobotFont;

public class HowToPlayPage extends MenuPage {

	public HowToPlayPage(MENUPAGEID id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		RobotFont.drawString("How to play goes here!", g, 100, 200);
		back.render(g);
	}

}
