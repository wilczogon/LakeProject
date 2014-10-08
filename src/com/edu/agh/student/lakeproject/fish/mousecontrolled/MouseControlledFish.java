package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.Color;
import java.awt.event.MouseMotionListener;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class MouseControlledFish extends Fish {

	public MouseControlledFish(LakeWorld lakeWorld, Vec2 position) {
		super(lakeWorld, position);
		setImage("rsc/ryba.png");
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new MouseControlledMuscles(super.body);
		lakeWorld.addMouseMotionListener((MouseMotionListener) muscles);
	}

	@Override
	public String getSpace() {
		return "com.edu.agh.student.lakeproject.fish.mousecontrolled";
	}

	@Override
	public Color getColor(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
