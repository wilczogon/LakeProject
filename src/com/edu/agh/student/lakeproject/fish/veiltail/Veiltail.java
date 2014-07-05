package com.edu.agh.student.lakeproject.fish.veiltail;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.Fish;

public class Veiltail extends Fish {

	public Veiltail() {
		super();
	}
	
	public Veiltail(float radius, Vec2 position){
		super(radius, position);
	}

	@Override
	public String getSpace() {
		return "com.edu.agh.student.lakeproject.fish.veiltail";
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new VeiltailMuscles(super.body);
	}

	

}
