package handler;

import game.RobotCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	private RobotCanvas canvasReference;
	
	public KeyHandler(RobotCanvas reference)
	{
		canvasReference = reference;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
