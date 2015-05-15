package state;

import handler.MouseHandler;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import constants.RobotImageLoader;
import utilities.RobotFont;
import utilities.Utility;
import button.SliderBar;
import button.SliderBar.SLIDERID;

public class SettingPage extends MenuPage {

	public SettingPage(MENUPAGEID id) {
		super(id);
		 
		barList.add(new SliderBar(100, 100, 200, 50, 0, 100,SLIDERID.VOLUME));
		barList.add( new SliderBar(500, 100, 200, 50, -10, 10,SLIDERID.OTHER));
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
		for(SliderBar bar : barList)
			bar.render(g);
	}
	
	public SliderBar getSelectedSlider() {
		for(int i = 0; i < barList.size(); i++)
		{
			if(barList.get(i).isMouseOver(MouseHandler.mouse.x, MouseHandler.mouse.y))
				return barList.get(i);
		}
		return null;
	}
	
	private ArrayList<SliderBar> barList = new ArrayList<SliderBar>();

}
