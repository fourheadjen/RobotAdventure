package entity;

import java.awt.Graphics;

import engine.Polygon;
import engine.Vector;
import game.RobotCanvas;

public class PhysicsSprite {
	
	private Vector acceleration;
	private Vector velocity;
	
	private Polygon bounds;
	
	private double mass;
	private double dampingC;
	private double dragC;

	public PhysicsSprite(int x, int y, int width, int height, double theta, Vector velocity, Vector acceleration, double mass, double dragC) {
		bounds=new Polygon(x, y, width, height,theta);
		if(acceleration==null)
			this.acceleration=new Vector(0,0);
		else
			this.acceleration=acceleration;
		if(velocity==null)
			this.velocity=new Vector(0,0);
		else
			this.velocity=velocity;
		this.mass=mass;
		//this.dampingC=dampingC;
		this.dragC=dragC;
	}

	public void render(Graphics g)
	{
		bounds.drawRect(g);
	}

	public void tick()
	{
		//move the thing-start Verlet integration
		bounds.move(velocity.vectorScale(RobotCanvas.timeStep).vectorAdd(acceleration.vectorScale(Math.pow(RobotCanvas.timeStep, 2)*.5)).vectorScale(100));//last scale makes the meters per pixel into centimeters per pixel
		
		//calculate the forces on the object
		Vector netForce=new Vector(0,(int)(mass*10));// force of gravity
		netForce=netForce.vectorSub(velocity.vectorScale(1.225*.5*dragC*bounds.area()));//force of drag
		//netForce=netForce.vectorSub(velocity.vectorScale(dampingC));//force of damping
		
		//check collision here and apply any needed forces??? or adjust momentum instead but less accurate
		
		//rotation??
		
		//Verlet integration finished
		Vector new_acceleration=netForce.vectorScale(1/mass);
		Vector avg_acceleration = acceleration.vectorAdd(new_acceleration).vectorScale(.5);
		velocity=velocity.vectorAdd(avg_acceleration.vectorScale(RobotCanvas.timeStep));
		//System.out.println(velocity.Y());//check the objects terminal velocity
		acceleration=new_acceleration;
	}

}
