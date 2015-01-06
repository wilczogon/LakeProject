package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.fish.Brain;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.userinterface.MainFrame;
import com.edu.agh.student.lakeproject.fish.Gender;

public class MouseControlledFish extends Fish {

	public MouseControlledFish(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
	  super(lakeWorld, in);
	}

	public MouseControlledFish(LakeWorld lakeWorld, Vec2 position) {
		super(lakeWorld, position, Color.WHITE);
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new MouseControlledMuscles(super.body);
		super.brain = new Brain();
		super.eye = new MouseControlledEye(lakeWorld, this);
		lakeWorld.addMouseMotionListener((MouseMotionListener) muscles);
	}

}
