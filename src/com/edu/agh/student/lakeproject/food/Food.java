package com.edu.agh.student.lakeproject.food;

import java.awt.Color;

import org.jbox2d.common.Vec2;
import java.io.*;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Food extends LakeObject implements Serializable {

	private int energy;
	private int expirationDate = 5000;
	
	public Food(LakeWorld lakeWorld, Vec2 position){
	  this(lakeWorld, 10, position, 100);
	}
	
	public Food(LakeWorld lakeWorld, float radius, Vec2 position, int energy){
		super(lakeWorld, radius, position, Color.GREEN);
		this.energy = energy;
		setImage("rsc/food.png");
	}
	
	/*public static Food readFromFile(ObjectInputStream ois){	//TODO
	  return (Food)ois.readObject();
	}
	
	public void writeToFile(ObjectInputStream ois){	//TODO
	  return (Food)ois.readObject();
	}*/

	@Override
	public String getType() {
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
		if(energy <= 0 or expirationDate <= 0)
			return false;
		return true;
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
	  energy = aInputStream.readInt();
	  radius = aInputStream.readFloat();
	  position = (Vec2)aInputStream.readObject();
	  super.color = Color.GREEN;
	  setImage("rsc/food.png");
	}

	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
	  aOutputStream.writeInt(energy);
	  aOutputStream.writeFloat(radius);
	  aOutputStream.writeObject(position);
	}

}
