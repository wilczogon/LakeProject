package com.edu.agh.student.lakeproject.lakeworld;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public abstract class LakeObject{
	
	protected Body body;
	protected LakeWorld lakeWorld;
	private float radius;
	private Vec2 position;
	
	public abstract String getType();
	
	public LakeObject(LakeWorld world, float radius, Vec2 position){
		this.lakeWorld = lakeWorld;
		this.position = position;
		this.radius = radius;
	}
	
	public void setBody(Body body){
		this.body = body;
	}

	public void interactWith(LakeObject lakeObject){
		return;
	}
	
	public abstract void move();
	
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void setInitialPosition(Vec2 position){
		this.position = position;
	}
	
	public Vec2 getInitialPosition(){
		return position;
	}
	
	public boolean isActive(){
		return true;
	}
}
