package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.fish.Brain;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.userinterface.MainFrame;
import com.edu.agh.student.lakeproject.fish.Gender;

public class MouseControlledFish extends Fish {

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
	
	/*@Override
	public void move(){
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(growthFactor*age + 1) + MAX_RADIUS);
		int x = 0;
		for(Object color: eye.getView().getPixels()){
			Graphics gd = MainFrame.getCanvas().getGraphics();
			gd.setColor((Color) color);
			gd.fillRect(x, 0, 10, 10);
			x+=10;
		}
		energy--;
		muscles.applyForces(null); //TODO
	}*/

}
