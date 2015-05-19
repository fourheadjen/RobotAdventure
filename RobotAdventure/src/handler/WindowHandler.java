package handler;

import game.RobotFrame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class WindowHandler implements ComponentListener{

	private RobotFrame frameRef;
	
	public WindowHandler(RobotFrame frame)
	{
		frameRef = frame;
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension size = e.getComponent().getSize();
		//System.out.println(size);
		
		int deltaX = Math.abs(size.width - previousSize.width);
		int deltaY = Math.abs(size.height - previousSize.height);
		
		if(deltaX > deltaY)
		{
			e.getComponent().setSize(   (int)Math.max( RobotFrame.GAME_MINIMUM_SIZE.width , size.width ),(int)Math.max( RobotFrame.GAME_MINIMUM_SIZE.height, size.width * aspectRatioToWidth ) );
		}else
		{
			e.getComponent().setSize(   (int)Math.max( RobotFrame.GAME_MINIMUM_SIZE.width , size.height * aspectRatioToHeight ),(int)Math.max( RobotFrame.GAME_MINIMUM_SIZE.height, size.height ) );
		}
		
		MouseHandler.mouse = new Point(-1,-1);
		
		frameRef.updateDimension();
		//System.out.println(e.getComponent().getSize());
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}
	
	private Dimension previousSize = RobotFrame.GAME_SIZE;
	
	private static final double aspectRatioToHeight = 16/9.0;
	private static final double aspectRatioToWidth = 9.0/16;
}
