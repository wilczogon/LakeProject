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
		
		/*try{	//Don't remove it for now - this is test code, to check if it works
		  OutputStream out = new BufferedOutputStream(new FileOutputStream("test.txt"));
		  ObjectOutputStream oout = new ObjectOutputStream(out);
		  this.writeToStream(oout);
		  oout.flush();
		  oout.close();
		  
		  InputStream in = new BufferedInputStream(new FileInputStream("test.txt"));
		  ObjectInputStream oin = new ObjectInputStream(in);
		  String className = (String)oin.readObject();
		  System.out.println(className + className.length());
		  Food food = new Food(lakeWorld, oin);
		  System.out.println(food.getEnergy());
		  oin.close();
		}catch(Exception e){
		  e.printStackTrace();
		}*/
	}

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
