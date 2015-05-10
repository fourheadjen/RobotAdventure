package engine;

public class Vector {
	
	private double x,y;

	public Vector()
	{
		this.x=0;
		this.y=0;
	}
	
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
		return new Vector((x*scalar),(y*scalar));
	}
	
	public double vectotDot(Vector a)
	{
		return (x*a.x)+(y*a.y);
	}
	
	public double vectorCross(Vector a)
	{
		return (x*a.y-y*a.x);
	}
	
	public double vectorLengthCross(Vector a)
	{
		return Math.abs(x*a.y-y*a.x);
	}
	
	public Vector vectorRotate(double degrees,Vector axis)//take another look at this to simplify the vector math
	{
		double tempX=this.x-axis.x;
		double tempY=this.y-axis.y;
		
		//System.out.println(tempX+" "+tempY);
		
		double cs=Math.cos(Math.toRadians(degrees));
		double sn=Math.sin(Math.toRadians(degrees));
		
		return new Vector(tempX*cs-tempY*sn+axis.x,tempX*sn+tempY*cs+axis.y);
	}
	
	public int X()
	{
		return (int)Math.round(x);
	}
	
	public int Y()
	{
		return (int)Math.round(y);
	}

}
