package engine;

import java.awt.Graphics;

import game.RobotCanvas;

public class PhysicsPoly extends Polygon
{
	
	private Vector acceleration;
	private Vector velocity;
	
	private double torque;
	private double omega;
	
	private double mass;
	private double dampingC;
	private double dragC;

	/*public PhysicsPoly(int x, int y, int width, int height, double theta, Vector velocity, double torque, double mass, double dragC) {
		super(x, y, width, height,theta);
		if(velocity==null)
			this.velocity=new Vector();
		else
			this.velocity=velocity;
		this.acceleration=new Vector();
		this.omega=0;
		this.mass=mass;
		//this.dampingC=dampingC;
		this.dragC=dragC;
		this.torque=torque;
	}*/
	
	public PhysicsPoly(int[] x,int[] y,double theta,Vector velocity,double torque,double mass,double dragC)
	{
		super(x,y,theta);
		if(velocity==null)
			this.velocity=new Vector();
		else
			this.velocity=velocity;
		this.acceleration=new Vector();
		this.omega=0;
		this.torque=torque;
		this.mass=mass;
		this.dragC=dragC;
	}

	public void render(Graphics g)
	{
		super.draw(g);
	}

	public void tick()
	{
		//move the thing-start Verlet integration
		super.move(velocity.vectorScale(RobotCanvas.timeStep).vectorAdd(acceleration.vectorScale(Math.pow(RobotCanvas.timeStep, 2)*.5)).vectorScale(100));//last scale makes the meters per pixel into centimeters per pixel
		
		//calculate the forces on the object
		Vector netForce=new Vector(0,(int)(mass*RobotCanvas.gravity));// force of gravity
		netForce=netForce.vectorSub(velocity.vectorScale(1.225*.5*dragC*super.getArea(false)));//force of drag
		//System.out.println(super.getCenter().X());
		//netForce=netForce.vectorSub(velocity.vectorScale(dampingC));//force of damping
		
		//check collision here and apply any needed forces??? or adjust momentum instead but less accurate
		
		//rotation??
		super.rotate(torque,null);
		super.getInertiaRelativeToCentroid();
		
		//Verlet integration finished
		Vector new_acceleration=netForce.vectorScale(1/mass);
		Vector avg_acceleration = acceleration.vectorAdd(new_acceleration).vectorScale(.5);
		velocity=velocity.vectorAdd(avg_acceleration.vectorScale(RobotCanvas.timeStep));
		//System.out.println(velocity.Y());//check the objects terminal velocity
		acceleration=new_acceleration;
	}

}
