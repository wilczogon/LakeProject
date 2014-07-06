package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class MouseControlledFish extends Fish {

	public MouseControlledFish(LakeWorld lakeWorld, float radius, Vec2 position) {
		super(lakeWorld, radius, position);
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

}
