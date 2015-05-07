package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Rect
{
	
	private int width;
	private int height;
	
	private double theta;
	
	private Vector topLeft;
	private Vector topRight;
	private Vector bottomLeft;
	private Vector bottomRight;

	public Rect(int x,int y,int width,int height)
	{
		this.width=width;
		this.height=height;
		
		this.topLeft=new Vector(x,y);
		this.topRight=new Vector(x+width,y);
		this.bottomLeft=new Vector(x,y+height);
		this.bottomRight=new Vector(x+width,y+height);
	}
	
	public Vector getCenter()
	{
		Vector diagonal=this.bottomRight.vectorSub(this.topLeft);
		Vector midpoint=this.topLeft.vectorAdd(diagonal.vectorScale(.5));
		return midpoint;
	}
	
	public void rotate(double angle)
	{
		this.theta+=angle;
		Vector center=this.getCenter();
		
		this.topLeft=this.topLeft.vectorRotate(angle, center);
		this.topRight=this.topRight.vectorRotate(angle, center);
		this.bottomLeft=this.bottomLeft.vectorRotate(angle, center);
		this.bottomRight=this.bottomRight.vectorRotate(angle, center);
	}
	
	public void move(Vector pos)
	{
		this.topLeft=this.topLeft.vectorAdd(pos);
		this.topRight=this.topRight.vectorAdd(pos);
		this.bottomLeft=this.bottomLeft.vectorAdd(pos);
		this.bottomRight=this.bottomRight.vectorAdd(pos);
	}
	
	public void drawRect(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawLine(this.topLeft.X(), this.topLeft.Y(), this.topRight.X(), this.topRight.Y());
		g.drawLine(this.topLeft.X(), this.topLeft.Y(), this.bottomLeft.X(), this.bottomLeft.Y());
		g.drawLine(this.bottomRight.X(), this.bottomRight.Y(), this.topRight.X(), this.topRight.Y());
		g.drawLine(this.bottomRight.X(), this.bottomRight.Y(), this.bottomLeft.X(), this.bottomLeft.Y());
	}
	
	public double area()
	{
		return width*height/100;//meters to centimeters
	}

}
