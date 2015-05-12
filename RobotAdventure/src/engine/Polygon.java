package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.security.SignatureException;


public class Polygon
{
	
	private double theta=0;
	private double area=-1;
	
	private Vector[] corners;

	/*public Polygon(int x,int y,int width,int height,double theta)
	{	
		corners=new Vector[4];
		corners[0]=new Vector(x,y);
		corners[1]=new Vector(x+width,y);
		corners[2]=new Vector(x+width,y+height);
		corners[3]=new Vector(x,y+height);
		getArea(true);
		rotate(theta,null);
	}*/
	
	public Polygon(int[] xPoints,int[] yPoints,double theta)
	{
		corners=new Vector[xPoints.length];
		for(int i=0;i<xPoints.length;i++)
			corners[i]=new Vector(xPoints[i],yPoints[i]);
		calculateArea();
		rotate(theta,null);
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
		//for(int i=0;i<corners.length;i++)
			//centroid=centroid.vectorAdd(corners[i]);
		return centroid;//.vectorScale(1.0/corners.length);
	}
	
	public void rotate(double angle,Vector[] points)
	{
		Vector axis=new Vector();
		if(points==null)
		{
			this.theta+=angle;
			axis=this.getCenter();
		}
		else
		{
			//set axis
		}
		//System.out.println(center.X()+"-"+center.Y());
		
		for(int i=0;i<corners.length;i++)
			corners[i]=corners[i].vectorRotate(angle, axis);
	}
	
	public void move(Vector pos)
	{
		for(int i=0;i<corners.length;i++)
		{
			corners[i]=corners[i].vectorAdd(pos);
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		int i=0;
		for(;i<corners.length-1;i++)
			g.drawLine(corners[i].X(), corners[i].Y(), corners[i+1].X(), corners[i+1].Y());
		g.drawLine(corners[i].X(), corners[i].Y(), corners[0].X(), corners[0].Y());
	}
	
	public void calculateArea()
	{
		//area of polygon: A=(.5)((x0*y1+x1*y2+x2*y0)-(y0*x1+y1*x2+y2*x0))
		double sum=0;
		int i=0;
		for(;i<corners.length-1;i++)
		{
			sum+=corners[i].vectorCross(corners[i+1]);
			//sum+=corners[i].X()*corners[i+1].Y();
			//sum-=corners[i].Y()*corners[i+1].X();
		}
		sum+=corners[i].vectorCross(corners[0]);
		//sum+=corners[i].X()*corners[0].Y();
		//sum-=corners[i].Y()*corners[0].X();
		area=sum/200;
	}
	
	public double getArea()
	{
		return area;
	}
	
	public Vector[] getCorners()
	{
		return corners;
	}

}
