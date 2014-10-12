package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.Color;
import java.awt.event.MouseMotionListener;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.fish.Gender;

public class MouseControlledFish extends Fish {

	public MouseControlledFish(LakeWorld lakeWorld, Vec2 position) {
		super(lakeWorld, position, Color.WHITE);
		setImage("rsc/ryba.png");
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new MouseControlledMuscles(super.body);
		super.eye = new MouseControlledEye(this, lakeWorld);
		lakeWorld.addMouseMotionListener((MouseMotionListener) muscles);
	}

	@Override
	public String getSpace() {
		return "com.edu.agh.student.lakeproject.fish.mousecontrolled";
	}
	
	@Override
	public void move(){
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(growthFactor*age + 1) + MAX_RADIUS);
		energy--;
		muscles.applyForces(null); //TODO
	}
	
	@Override
	public void initReproductionOrgans(Gender gender){
	  // do nothing
	}

}
