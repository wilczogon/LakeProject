package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.lakeworld.LakeObject;

public abstract class Fish extends LakeObject {

	protected Eye eye;
	protected Brain brain;
	protected Muscles muscles;

	public Fish() {
		super();
	}
	
	public Fish(float radius, Vec2 position){
		super(radius, position);
	}
	
	@Override
	public String getType() {
		return "Fish";
	}
	
	public abstract String getSpace();
	
	@Override
	public void move(){
		muscles.applyForces(null);//brain.decideMovement(eye.getView())); //TODO
	}

}
