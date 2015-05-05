package game;

import handler.KeyHandler;
import handler.MouseHandler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import state.GameStateManager;

public class RobotCanvas extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905093269733175517L;

	public RobotCanvas(int width, int height)
	{
		setSize(width,height);
		running = true;
		
		mouseHandler = new MouseHandler(this);
		keyHandler = new KeyHandler(this);
		
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addKeyListener(keyHandler);
		
		manager = new GameStateManager(this);
	}
	
	public void tick()
	{
		//TODO: Update things here.
		manager.tick();
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
		manager.render(g);
		
		g.dispose();
		bs.show();
	}
	
	
	@Override
	public void run() {
		GameTimer gameTimer = new GameTimer();
		
		while(running)
		{
			if(!gamePaused)
			{
				if(gameTimer.getElapsedTime() > 30)
				{
					tick();
					gameTimer.restart();
				}
				render();
			}
		}
	}
	
	/*
	 * getters / setters
	 */
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameStateManager getManager() {
		return manager;
	}
	
	public boolean isGamePaused() {
		return gamePaused;
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}
	
	/*
	 * Variables
	 */
	
	

	private boolean running = false;
	private boolean gamePaused = false;
	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	private GameStateManager manager;
}
