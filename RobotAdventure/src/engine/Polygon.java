package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.security.SignatureException;


public class Polygon
{
	
	private double theta=0;
	
	private Vector[] corners;

	public Polygon(int x,int y,int width,int height,double theta)
	{	
		corners=new Vector[4];
		corners[0]=new Vector(x,y);
		corners[1]=new Vector(x+width,y);
		corners[2]=new Vector(x+width,y+height);
		corners[3]=new Vector(x,y+height);
		rotate(theta);
	}
	
	public Polygon(int[] xPoints,int[] yPoints,double theta)
	{
		corners=new Vector[xPoints.length];
		for(int i=0;i<xPoints.length;i++)
			corners[i]=new Vector(xPoints[i],yPoints[i]);
		rotate(theta);
	}
	
	public Vector getCenter()//uses the summation equation for finding the centroid of a non-self-intersecting closed polygon
	{
		Vector centroid=new Vector(0,0);
		double signedArea=0;
		double partialSignedArea=0;
		int i=0;
		
		for(;i<corners.length-1;i++)
		{
			partialSignedArea=corners[i].vectorCross(corners[i+1]);
			signedArea+=partialSignedArea;
			centroid=centroid.vectorAdd(corners[i].vectorAdd(corners[i+1]).vectorScale(partialSignedArea));
		}
		
		partialSignedArea=corners[i].vectorCross(corners[0]);
		signedArea+=partialSignedArea;
		centroid=centroid.vectorAdd(corners[i].vectorAdd(corners[0]).vectorScale(partialSignedArea));
		
		signedArea*=.5;
		centroid=centroid.vectorScale(1.0/(6*signedArea));
		
		//System.out.println(centroid.X()+" "+centroid.Y());
		return centroid;
	}
	
	public void rotate(double angle)
	{
		this.theta+=angle;
		Vector center=this.getCenter();
		System.out.println(center.X()+"-"+center.Y());
		
		for(int i=0;i<corners.length;i++)
			corners[i]=corners[i].vectorRotate(angle, center);
	}
	
	public void move(Vector pos)
	{
		for(int i=0;i<corners.length;i++)
		{
			corners[i]=corners[i].vectorAdd(pos);
		}
	}
	
	public void drawRect(Graphics g)
	{
		g.setColor(Color.BLACK);
		int i=0;
		for(;i<corners.length-1;i++)
			g.drawLine(corners[i].X(), corners[i].Y(), corners[i+1].X(), corners[i+1].Y());
		g.drawLine(corners[i].X(), corners[i].Y(), corners[0].X(), corners[0].Y());
	}
	
	public double area()
	{
		//return width*height/100;//meters to centimeters
		return 0;//fix for all polygons
	}

}
