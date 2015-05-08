package state;

import game.RobotCanvas;
import handler.MouseHandler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import constants.RobotImageLoader;
import utilities.RobotFont;
import button.BUTTONID;
import button.FauxButton;

public class Menu extends GameState {
	
	public Menu(STATE stateID) {
		super(stateID);
		buttonList = new ArrayList<FauxButton>();
		buttonList.add(new FauxButton(50, 300, 300, 100,BUTTONID.HOW_TO_PLAY,RobotImageLoader.HOW_TO_PLAY_IMAGE));
		buttonList.add(new FauxButton(650,300,300,100,BUTTONID.EXIT,RobotImageLoader.EXIT_IMAGE));
		buttonList.add(new FauxButton(50,100,300,100,BUTTONID.STARTGAME,RobotImageLoader.START_GAME_IMAGE));
		buttonList.add(new FauxButton(650,100,300,100,BUTTONID.SETTINGS,RobotImageLoader.SETTINGS_IMAGE));
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
		for(FauxButton button : buttonList)
		{
			button.render(g);
			if(button.isMouseOver(MouseHandler.mouse))
			{
				g.setColor(new Color(100,100,100,100));
				g.fillRect(button.getX(), button.getY(), button.getWidth(), button.getHeight());
			}
		}
	}
	
	public ArrayList<FauxButton> getButtonList(){return buttonList;}
	
	public FauxButton getSelectedButton()
	{
		for(int i = 0; i < buttonList.size(); i++)
		{
			if(buttonList.get(i).isSelected())
			{
				return buttonList.get(i);
			}
		}
		
		return null;
	}
	
	private ArrayList<FauxButton> buttonList;	
	private int score;
}
