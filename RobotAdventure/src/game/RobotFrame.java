package game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class RobotFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039879089105886906L;

	public RobotFrame() {
		super(GAME_NAME);
		setSize(GAME_SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(false); //so we can just click and press on canvas
		//setResizable(false);
		init();
	}
	
	public void init()
	{
		robotCanvas = new RobotCanvas(GAME_WIDTH,GAME_HEIGHT,this);
		System.out.println(GAME_SIZE);
		System.out.println(Toolkit.getDefaultToolkit().getScreenResolution());
		add(robotCanvas);
		gameloop = new Thread(robotCanvas);
		gameloop.start();
	}

	public static void main(String[] args) {
		new RobotFrame().setVisible(true);
	}

	/*
	 * Variables
	 */
	
	public void updateDimension()
	{
		GAME_WIDTH = (int) getSize().getWidth();
		GAME_HEIGHT = (int) getSize().getHeight();
	}
	
	private RobotCanvas robotCanvas;
	private Thread gameloop;
	
	public static final Dimension GAME_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static int GAME_WIDTH = GAME_SIZE.width;
	public static int GAME_HEIGHT = GAME_SIZE.height;
	
	public static final String GAME_NAME = "Robot Adventure";
	
	
}
