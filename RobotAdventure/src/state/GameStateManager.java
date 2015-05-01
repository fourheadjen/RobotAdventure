package state;

import game.RobotCanvas;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameStateManager { //<Extend GameClass interface in utilites package>

	private RobotCanvas canvasReference;
	private ArrayList<GameState> gameStates;
	private STATE currentState;
	
	public GameStateManager(RobotCanvas ref)
	{
		canvasReference = ref;
	}
	
	public void setCurrentState(STATE state)
	{
		currentState = state;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		gameStates.get(currentState.ordinal()).render(g);
	}
	
}
