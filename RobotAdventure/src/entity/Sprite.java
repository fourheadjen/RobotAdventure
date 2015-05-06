package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Sprite {

	public static final int RIGHT = 0,
							DOWN = 1,
							LEFT = 2,
							UP = 3;
	
	public static final int OPTION_X = 0,
							OPTION_Y = 1,
							OPTION_WIDTH = 2,
							OPTION_HEIGHT = 3;
	
	private Rectangle bounds;
	private ArrayList<Rectangle> collisionBoundsList;
	
	public Sprite(int x, int y, int width, int height)
	{
		bounds = new Rectangle(x,y,width,height);
		collisionBoundsList = new ArrayList<Rectangle>();
		collisionBoundsList.add(bounds);
	}
	
	public void addCollisionBounds(Rectangle rect)
	{
		if(!collisionBoundsList.contains(rect))
			collisionBoundsList.add(rect);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public boolean collidesWith(Sprite sprite)
	{
		for(int i = 0; i < collisionBoundsList.size(); i++)
		{
			for(int j = 0; j < sprite.collisionBoundsList.size(); j++)
			{
				if(collisionBoundsList.get(i).intersects(sprite.collisionBoundsList.get(j)))
					return true;
			}
		}
		
		return false;
	}
	
	public void updateBounds(int index, int option, int value)
	{
		if(index >= collisionBoundsList.size())
			return;
		
		Rectangle bounds = collisionBoundsList.get(index);
		
		switch(option)
		{
		case OPTION_X:
			bounds.x = value;
			break;
		case OPTION_Y:
			bounds.y = value;
			break;
		case OPTION_WIDTH:
			bounds.width = value;
			break;
		case OPTION_HEIGHT:
			bounds.height = value;
			break;
		}
	}
	
	public void updateBounds(int index, Rectangle rectangle)
	{
		if(index >= collisionBoundsList.size())
			return;
		
		collisionBoundsList.set(index, rectangle);
	}
}
