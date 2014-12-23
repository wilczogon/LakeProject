package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.dynamics.Body;
import org.jbox2d.common.Vec2;

public class Muscles {
	
	protected Body body;
	protected double strength = 60; //the stronger fish, the faster it swims
	protected double agility = 10; //how fats it can change direction

	public Muscles(Body body){
		this.body = body;
	}

	public void applyForces(MovementDecision decision){
	  double angle = body.getAngle() + decision.getAngle()*agility;
	  double speed = decision.getSpeed()*strength;
	  body.m_sweep.a = (float)angle;
	  body.applyForceToCenter(new Vec2((float)(Math.sin((angle/180)*Math.PI)*speed), -(float)(Math.cos((angle/180)*Math.PI)*speed)));
	}
}
