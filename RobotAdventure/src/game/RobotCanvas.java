package game;

import handler.KeyHandler;
import handler.MouseHandler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import state.GameStateManager;
import engine.Vector;
import entity.PhysicsSprite;

public class RobotCanvas extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5905093269733175517L;

	public RobotCanvas(int width, int height,RobotFrame frameRef)
	{
		setSize(width,height);
		running = true;
		
		mouseHandler = new MouseHandler(this);
		keyHandler = new KeyHandler(this);
		
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addKeyListener(keyHandler);
		
		manager = new GameStateManager(this);
		
		buffer = new BufferedImage(BUFFER_WIDTH,BUFFER_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		
		xRatio = (double)RobotFrame.GAME_WIDTH/BUFFER_WIDTH;
		yRatio = (double)RobotFrame.GAME_HEIGHT/BUFFER_HEIGHT;
		
		this.robotFrameReference = frameRef;
	}
	
	public void tick()
	{
		//TODO: Update things here.
		manager.tick();
		testBox.tick();
		
		if(first)
		{
			first = !first;
			System.out.println(buffer.getWidth() + "," + buffer.getHeight());
		}
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		
		
		
		Graphics b = buffer.getGraphics();
		b.setColor(Color.WHITE);
		b.fillRect(0, 0, BUFFER_WIDTH, BUFFER_HEIGHT);
		//TODO: Draw stuff here	
		manager.render(b);
		testBox.render(b);
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(buffer, 0, 0,RobotFrame.GAME_WIDTH, RobotFrame.GAME_HEIGHT, null);
		
		b.dispose();
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
				if(gameTimer.getElapsedTime() > MILLISECOND_STEP)
				{
					tick();
					gameTimer.restart();
					render();
				}
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
	
	
	public RobotFrame getRobotFrameReference() {
		return robotFrameReference;
	}




	/*
	 * Variables
	 */
	private boolean first = true;
	
	private BufferedImage buffer;
	
	public static final double TIME_STEP=.016667;
	public static final int MILLISECOND_STEP=(int) (TIME_STEP*1000);

	public static final int BUFFER_WIDTH = 16<<6;
	public static final int BUFFER_HEIGHT = 9<<6;
	
	public static final int X_CENTERED_TEXT = BUFFER_WIDTH/2 - BUFFER_WIDTH/8; //1/2 - 1/8
	public static final int Y_BOTTOM_TEXT = BUFFER_HEIGHT - BUFFER_HEIGHT/4; //1 - 1/4
	
	public static double xRatio;
	public static double yRatio;
	
	private boolean running = false;
	private boolean gamePaused = false;
	
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	private GameStateManager manager;
	
	private RobotFrame robotFrameReference;
	
	private PhysicsSprite testBox=new PhysicsSprite(0, 100, 50, 50, null, new Vector(5,-5), 50, 0, 1.05);
}
