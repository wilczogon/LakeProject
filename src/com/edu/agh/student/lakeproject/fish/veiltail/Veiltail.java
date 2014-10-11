package com.edu.agh.student.lakeproject.fish.veiltail;

import java.awt.Color;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Veiltail extends Fish {
	
	public Veiltail(LakeWorld lakeWorld, Vec2 position){
		super(lakeWorld, position, Color.YELLOW);
		setImage("rsc/ryba.png");
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
