package state;

import java.awt.Graphics;

public abstract class GameState {
		
	private STATE stateID;
	
	public GameState(STATE stateID)
	{
		this.stateID = stateID;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	public STATE getStateID() {return stateID;}
	
}
