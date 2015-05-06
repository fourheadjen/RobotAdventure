package engine;

public class Vector {
	
	private double x,y;

	public Vector(double x,double y)
	{
		this.x=x;
		this.y=y;
	}
	
	public static Vector vectorAdd(Vector a,Vector b)
	{
		return new Vector(a.x+b.x,a.y+b.y);
	}
	
	public static Vector vectorSub(Vector a,Vector b)
	{
		return new Vector(a.x-b.x,a.y-b.y);
	}
	
	public static Vector vectorMult(Vector a,double scalar)
	{
		return new Vector(a.x*scalar,a.y*scalar);
	}
	
	public static double vectotDot(Vector a,Vector b)
	{
		return (a.x*b.x)+(a.y*b.y);
	}
	
	public static double vectorCross(Vector a,Vector b)
	{
		return (a.x*b.y-a.y*b.x);
	}
	
	public static Vector vectorRotate(Vector a,double degrees,Vector axis)
	{
		double x=a.x-axis.x;
		double y=a.y-axis.y;
		
		double cs=Math.cos(Math.toRadians(degrees));
		double sn=Math.sin(Math.toRadians(degrees));
		
		return new Vector(x*cs-y*sn,x*sn+y*cs);
	}

}
