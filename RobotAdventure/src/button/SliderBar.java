package button;

import game.RobotCanvas;
import game.RobotFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import utilities.RobotFont;

public class SliderBar{

	private Rectangle bounds;
	private Slider slider;
	
	public SliderBar(int x, int y, int width, int height, int min, int max)
	{
		bounds = new Rectangle(x,y,width + (width / 100 * 10),height);
		slider = new Slider(x,y,width,height,min, max,this);
	}
	
	public boolean isMouseOver(int x, int y)
	{
		if(RobotFrame.isMaximized())		
		{
			 if(bounds.contains(x / RobotCanvas.xRatio, y / RobotCanvas.yRatio))
				return true; 
		}else
			if(bounds.contains(x,y))
				return true;
			
		return false;
	}
	
	public Slider getSlider(){
		return slider;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(bounds.x,bounds.y, bounds.width, bounds.height);
		slider.render(g);
		RobotFont.drawString(slider.min+"", g, bounds.x - RobotFont.getFontWidth(slider.min+"")-4, bounds.y+bounds.height/2-8);
		RobotFont.drawString(slider.max+"",g,bounds.x + bounds.width +  4,  bounds.y+bounds.height/2-8);
		RobotFont.drawString("Value: " + slider.value, g, bounds.x, bounds.y + bounds.height + 4);
	}
	
	public class Slider
	{
		private int value;
		private int min, max;
		private Rectangle bounds;
		private int updateIncrement;
		private SliderBar bar;
		
		public Slider(int x, int y,int width,int height, int min, int max,SliderBar bar)
		{
			this.min = min;
			this.max = max;
			value = min;
			updateIncrement = width / Math.abs(min-max); 
			bounds = new Rectangle(x,y,width / 100 * 10,height);
			this.bar = bar;
		}
		
		public int getValue()
		{
			return value;
		}
		
		public boolean isMouseOver(int x, int y)
		{
			if(RobotFrame.isMaximized())		
			{
				 if(bounds.contains(x / RobotCanvas.xRatio, y / RobotCanvas.yRatio))
					return true; 
			}else
				if(bounds.contains(x,y))
					return true;
				
			return false;
		}
		
		public void update(int x)
		{
			if(x - bounds.x < 0)
			{
				value = Math.max(min,value-1);
				bounds.x = Math.max(0,bounds.x - updateIncrement);
			}
			else
			{
				value = Math.min(max, value+1);
				bounds.x = Math.min(bar.bounds.x + bar.bounds.width - bounds.width,bounds.x +updateIncrement);
			}
		}
		
		public void render(Graphics g)
		{
			g.setColor(Color.CYAN);
			g.drawRect(bounds.x,bounds.y, bounds.width, bounds.height);
		}
	}
}

enum SLIDERID
{
	VOLUME,
	OTHER
}
