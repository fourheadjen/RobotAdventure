package engine;

public class Vector {
	
	private double x,y;

	public Vector(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public Vector vectorAdd(Vector a)
	{
		return new Vector(x+a.x,y+a.y);
	}
	
	public Vector vectorSub(Vector a)
	{
		return new Vector(x-a.x,y-a.y);
	}
	
	public Vector vectorScale(double scalar)
	{
		return new Vector(x*scalar,y*scalar);
	}
	
	public double vectotDot(Vector a)
	{
		return (x*a.x)+(y*a.y);
	}
	
	public double vectorCross(Vector a)
	{
		return (x*a.y-y*a.x);
	}
	
	public Vector vectorRotate(double degrees,Vector axis)
	{
		double x=this.x-axis.x;
		double y=this.y-axis.y;
		
		double cs=Math.cos(Math.toRadians(degrees));
		double sn=Math.sin(Math.toRadians(degrees));
		
		return new Vector(x*cs-y*sn,x*sn+y*cs);
	}

}
