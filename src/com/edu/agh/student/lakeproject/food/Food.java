package com.edu.agh.student.lakeproject.food;

import java.awt.Color;

import org.jbox2d.common.Vec2;
import java.io.*;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Food extends LakeObject{

	private int energy;
	private int expirationDate = 5000;
	
	public Food(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
	  super(lakeWorld, in);
	  energy = in.readInt();
	  expirationDate = in.readInt();
	  setImage("rsc/food.png");
	}
	
	public Food(LakeWorld lakeWorld, Vec2 position){
	  this(lakeWorld, 10, position, 100);
	}
	
	public Food(LakeWorld lakeWorld, float radius, Vec2 position, int energy){
		super(lakeWorld, radius, position, Color.GREEN);
		this.energy = energy;
		setImage("rsc/food.png");
	}

	@Override
	public final String getType() {
		return LakeConfiguration.foodTypeName;
	}

	@Override
	public void move() {
		expirationDate--;
	}
	
	public int getEnergy(){
	  int tmp = energy;
	  energy = 0;
	  return tmp;
	}
	
	@Override
	public boolean isActive(){
		if(energy <= 0 || expirationDate <= 0)
			return false;
		return true;
	}
	
	@Override
	public void writeToStream(ObjectOutputStream out) throws IOException{
	  super.writeToStream(out);
	  out.writeInt(energy);
	  out.writeInt(expirationDate);
	}
}
