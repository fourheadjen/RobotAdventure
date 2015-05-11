package handler;

import game.RobotCanvas;
import game.RobotFrame;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import state.Menu;
import state.STATE;
import button.FauxButton;

public class MouseHandler implements MouseListener, MouseMotionListener {

	public static Point mouse;
	private RobotCanvas canvasReference;
	
	public MouseHandler(RobotCanvas reference)
	{
		canvasReference = reference;
		mouse = new Point(-1,-1);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse = e.getPoint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		Point p = e.getPoint();
		
		switch(canvasReference.getManager().getCurrentState())
		{
		case MENU:
			
			if(e.getButton() == MouseEvent.BUTTON1) //LEFT MOUSE BUTTON
			{
				//System.out.println("MENU SCREEN CLICKED W/ LEFT MOUSE");
				
				Menu menu = (Menu)canvasReference.getManager().getCurrentGameState();
				
				FauxButton button = menu.getSelectedButton();
				
				if(button != null)
				{
					//System.out.println(button);
					
					switch(button.getID())
					{
					case STARTGAME:
						
						menu.getGameStateManager().setCurrentState(STATE.GAMEPLAY);
						
						break;
					case EXIT:
						System.exit(0);
						break;
					default: //how to play or settings
						menu.changePage(button.getID());
						break;
					}
					
				}
				
			}
			else if(e.getButton() == MouseEvent.BUTTON3)
			{
				System.out.println("MENU SCREEN CLICKED W/ RIGHT MOUSE");
			}else if(e.getButton()==MouseEvent.BUTTON2) //MIDDLE MOUSE BUTTON
			{
				System.out.println("RESIZING WINDOW... (TEMP)");
				if(canvasReference.getRobotFrameReference().getSize().equals(RobotFrame.GAME_FULLSCREEN_SIZE))
				{
					canvasReference.getRobotFrameReference().setSize(RobotFrame.GAME_WINDOWED_SIZE);
					RobotFrame.GAME_SIZE = RobotFrame.GAME_WINDOWED_SIZE;
					canvasReference.setSize(RobotFrame.GAME_WINDOWED_SIZE);
				}else
				{
					canvasReference.getRobotFrameReference().setSize(RobotFrame.GAME_FULLSCREEN_SIZE);
					RobotFrame.GAME_SIZE = RobotFrame.GAME_FULLSCREEN_SIZE;
					canvasReference.setSize(RobotFrame.GAME_FULLSCREEN_SIZE);
				}
				canvasReference.getRobotFrameReference().setLocationRelativeTo(null);
				canvasReference.getRobotFrameReference().updateDimension();
			}
			break;
		case GAMEPLAY:
			
			System.out.println("GAMEPLAY SCREEN CLICKED");
			
			break;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	/////
	
	public Point getMouse()
	{
		return mouse;
	}
	
}
