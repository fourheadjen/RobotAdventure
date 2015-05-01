package game;

import javax.swing.JFrame;

public class RobotFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9039879089105886906L;

	public RobotFrame() {
		super(GAME_NAME);
		setSize(GAME_WIDTH,GAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setFocusable(false); //so we can just click and press on canvas
		setResizable(false);
		init();
	}
	
	public void init()
	{
		robotCanvas = new RobotCanvas(GAME_WIDTH, GAME_HEIGHT);
		
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
	
	private RobotCanvas robotCanvas;
	private Thread gameloop;
	
	public static final int GAME_WIDTH = 16 << 6;
	public static final int GAME_HEIGHT = 9 << 6;
	public static final String GAME_NAME = "Robot Adventure";
	
	
}
