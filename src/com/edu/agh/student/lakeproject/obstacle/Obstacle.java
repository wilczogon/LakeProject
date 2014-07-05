package com.edu.agh.student.lakeproject.obstacle;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Obstacle extends LakeObject {
	
	public Obstacle(LakeWorld lakeWorld, float radius, Vec2 position){
		super(lakeWorld, radius, position);
	}

	@Override
	public String getType() {
		return LakeConfiguration.obstacleTypeName;
	}

	@Override
	public void move() {
		return;
	}

}
