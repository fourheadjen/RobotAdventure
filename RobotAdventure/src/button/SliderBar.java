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
			 if(slider.bounds.contains(x / RobotCanvas.xRatio, y / RobotCanvas.yRatio))
				return true; 
		}else
			if(slider.bounds.contains(x,y))
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
		
		public boolean isMouseOver(double x, double y)
		{
			if(bounds.contains(x,y))
				return true;
				
			return false;
		}
		
		public void update(int x)
		{
			if(RobotFrame.isMaximized())
			{
				bounds.x = (int)((2 * x / RobotCanvas.xRatio - bounds.width) / 2.0);
				if(bounds.x < bar.bounds.x)
					bounds.x = bar.bounds.x;
				if(bounds.x + bounds.width > bar.bounds.x + bar.bounds.width)
					bounds.x = bar.bounds.x + bar.bounds.width - bounds.width;
				
				value = (bounds.x - bar.bounds.x + min)/ updateIncrement;
			}else
			{
				bounds.x = (2 * x - bounds.width) / 2;
				if(bounds.x < bar.bounds.x)
					bounds.x = bar.bounds.x;
				if(bounds.x + bounds.width > bar.bounds.x + bar.bounds.width)
					bounds.x = bar.bounds.x + bar.bounds.width - bounds.width;
				value = (bounds.x - bar.bounds.x + min)/ updateIncrement;
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
