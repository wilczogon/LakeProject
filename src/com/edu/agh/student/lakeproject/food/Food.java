package com.edu.agh.student.lakeproject.food;

import java.awt.Color;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Food extends LakeObject {

	private int energy;
	
	public Food(LakeWorld lakeWorld, float radius, Vec2 position, int energy){
		super(lakeWorld, radius, position);
		this.energy = energy;
		setImage("rsc/food.png");
	}

	@Override
	public String getType() {
		return LakeConfiguration.foodTypeName;
	}

	@Override
	public void move() {
		return;
	}
	
	public int getEnergy(){
	  int tmp = energy;
	  energy = 0;
	  return tmp;
	}
	
	@Override
	public boolean isActive(){
		if(energy <= 0)
			return false;
		return true;
	}

	@Override
	public Color getColor(float distance) {
		// TODO Auto-generated method stub
		return null;
	}

}
