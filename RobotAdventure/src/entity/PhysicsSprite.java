package entity;

import java.awt.Graphics;

import engine.Rect;
import engine.Vector;
import game.RobotCanvas;

public class PhysicsSprite extends Sprite {
	
	private Vector acceleration;
	private Vector velocity;
	
	private double mass;
	private double dampingC;
	private double dragC;

	public PhysicsSprite(int x, int y, int width, int height, Vector acceleration, Vector velocity, double mass,double dampingC,double dragC) {
		super(x, y, width, height);
		if(acceleration==null)
			this.acceleration=new Vector(0,0);
		else
			this.acceleration=acceleration;
		if(velocity==null)
			this.velocity=new Vector(0,0);
		else
			this.velocity=velocity;
		this.mass=mass;
		this.dampingC=dampingC;
		this.dragC=dragC;
	}

	@Override
	public void render(Graphics g)
	{
		bounds.drawRect(g);
	}

	@Override
	public void tick()
	{
		//move the thing-start Verlet integration
		bounds.move(velocity.vectorScale(RobotCanvas.TIME_STEP).vectorAdd(acceleration.vectorScale(Math.pow(RobotCanvas.TIME_STEP, 2)*.5)).vectorScale(100));//last scale makes the meters per pixel into centimeters per pixel
		
		//calculate the forces on the object
		Vector netForce=new Vector(0,(int)(mass*10));// force of gravity
		netForce=netForce.vectorSub(velocity.vectorScale(1.225*.5*dragC*bounds.area()));//force of drag
		//netForce=netForce.vectorSub(velocity.vectorScale(dampingC));//force of damping
		
		//check collision here and apply any needed forces??? or adjust momentum instead but less accurate
		
		//rotation??
		
		//Verlet integration finished
		Vector new_acceleration=netForce.vectorScale(1/mass);
		Vector avg_acceleration = acceleration.vectorAdd(new_acceleration).vectorScale(.5);
		velocity=velocity.vectorAdd(avg_acceleration.vectorScale(RobotCanvas.TIME_STEP));
		//System.out.println(velocity.Y());//check the objects terminal velocity
		acceleration=new_acceleration;
	}

}
