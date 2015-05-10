package state;

import game.RobotCanvas;
import handler.AudioHandler.SOUND;
import handler.MouseHandler;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.event.MenuListener;

import utilities.RobotFont;
import button.BUTTONID;
import button.FauxButton;
import constants.RobotImageLoader;

public class Menu extends GameState {
	
	public Menu(STATE stateID,GameStateManager ref) {
		super(stateID,ref);
		buttonList = new ArrayList<FauxButton>();
		buttonList.add(new FauxButton(50, 300, 300, 100,BUTTONID.HOW_TO_PLAY,RobotImageLoader.HOW_TO_PLAY_IMAGE));
		buttonList.add(new FauxButton(650,300,300,100,BUTTONID.EXIT,RobotImageLoader.EXIT_IMAGE));
		buttonList.add(new FauxButton(50,100,300,100,BUTTONID.STARTGAME,RobotImageLoader.START_GAME_IMAGE));
		buttonList.add(new FauxButton(650,100,300,100,BUTTONID.SETTINGS,RobotImageLoader.SETTINGS_IMAGE));
		
		menuPageList = new ArrayList<MenuPage>();
		menuPageList.add(new HowToPlayPage(MENUPAGEID.HOW_TO_PLAY));
		menuPageList.add(new SettingPage(MENUPAGEID.SETTINGS));
		currentPage = -1;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, RobotCanvas.BUFFER_WIDTH, RobotCanvas.BUFFER_HEIGHT);
		
		if(currentPage == -1)
		{
			RobotFont.drawMultiLineString("Robot Adventure\nBy:\nJonathan Collins\nSkylar Donlevy\nJennifer Vu", g, RobotCanvas.X_CENTERED_TEXT, RobotCanvas.Y_BOTTOM_TEXT,RobotFont.CENTER);

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
		else
		{
			menuPageList.get(currentPage).render(g);
			FauxButton button = menuPageList.get(currentPage).getBackButton();
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
		
		if(currentPage == -1)
		{
			for(int i = 0; i < buttonList.size(); i++)
			{
				if(buttonList.get(i).isSelected())
				{
					return buttonList.get(i);
				}
			}
		}
		else
		{
			if(menuPageList.get(currentPage).getBackButton().isSelected())
				return menuPageList.get(currentPage).getBackButton();
		}
	
		return null;
	}
	
	public void changePage(BUTTONID buttonID)
	{
		switch(buttonID)
		{
		case SETTINGS:
			
			currentPage = MENUPAGEID.SETTINGS.ordinal();
			
			break;
		case HOW_TO_PLAY:
			
			currentPage = MENUPAGEID.HOW_TO_PLAY.ordinal();
			
			break;
		case BACK:
			
			currentPage = -1;
			break;
		}
	}
	
	public MenuPage getCurrentPage()
	{
		return menuPageList.get(currentPage);
	}
	
	private ArrayList<FauxButton> buttonList;
	private ArrayList<MenuPage> menuPageList;
	
	private int currentPage;
}
