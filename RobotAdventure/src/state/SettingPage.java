package state;

import java.awt.Graphics;
import java.util.prefs.BackingStoreException;

import utilities.RobotFont;

public class SettingPage extends MenuPage {

	public SettingPage(MENUPAGEID id) {
		super(id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		RobotFont.drawString("SETTINGS GO HERE!", g, 200, 200);
		back.render(g);
	}

}
