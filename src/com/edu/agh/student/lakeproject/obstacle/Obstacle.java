package com.edu.agh.student.lakeproject.obstacle;

import java.awt.Color;

import org.jbox2d.common.Vec2;
import java.io.*;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Obstacle extends LakeObject {

	public Obstacle(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
	  super(lakeWorld, in);
	  setImage("rsc/skala.png");
	}

	public Obstacle(LakeWorld lakeWorld, Vec2 position){
	  this(lakeWorld, 20, position);
	}
	
	public Obstacle(LakeWorld lakeWorld, float radius, Vec2 position){
		super(lakeWorld, radius, position, Color.GRAY);
		setImage("rsc/skala.png");
	}

	@Override
	public final String getType() {
		return LakeConfiguration.obstacleTypeName;
	}

	@Override
	public void move() {
		return;
	}

}
