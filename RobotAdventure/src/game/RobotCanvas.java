package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class RobotCanvas extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905093269733175517L;

	public RobotCanvas(int width, int height)
	{
		setSize(width,height);
		running = true;
	}
	
	public void tick()
	{
		//TODO: Update things here.
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, RobotFrame.GAME_WIDTH, RobotFrame.GAME_HEIGHT);
		//TODO: Draw stuff here	
		
		g.dispose();
		bs.show();
	}
	
	
	@Override
	public void run() {
		GameTimer gameTimer = new GameTimer();
		
		while(running)
		{
			if(gameTimer.getElapsedTime() > 30)
			{
				tick();
				gameTimer.restart();
			}
			
			render();
		}
	}
	
	/*
	 * Variables
	 */
	private boolean running = false;
}
