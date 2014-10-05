package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;

public abstract class Fish extends LakeObject {

	protected Eye eye;
	protected Brain brain;
	protected Muscles muscles;
	protected ReproductiveOrgans reproductiveOrgans;
	protected int energy = 1000;	// does fish eat something?
	protected int health = 1000;	// does fish fight with others?
	protected int age = 0;
	protected float growthFactor = 1/200;
	
	public Fish(LakeWorld lakeWorld, float radius, Vec2 position){
		super(lakeWorld, radius, position);
	}
	
	@Override
	public String getType() {
		return LakeConfiguration.fishTypeName;
	}
	
	public abstract String getSpace();
	
	@Override
	public void move(){
		energy--;
		muscles.applyForces(null);//brain.decideMovement(eye.getView())); //TODO
	}
	
	@Override
	public void interactWith(LakeObject lakeObject){
		if(lakeObject.getType().equals(LakeConfiguration.fishTypeName))
			interactWith((Fish)lakeObject);
		else if(lakeObject.getType().equals(LakeConfiguration.obstacleTypeName))
			interactWith((Obstacle)lakeObject);
		else if(lakeObject.getType().equals(LakeConfiguration.foodTypeName))
			interactWith((Food)lakeObject);
		
	}
	
	protected void interactWith(Fish fish){
		if(fish.getSpace().equals(getSpace())){
			// mój gatunek, sprawdź płeć
		} else{
			health--;
		}
	}
	
	protected void interactWith(Obstacle obstacle){
		health--;
	}
	
	protected void interactWith(Food food){
		energy+= food.getEnergy();
		if(energy > 1000)	//TODO
		  energy = 1000;
	}
	
	@Override
	public boolean isActive(){
		if(health <= 0 || energy <= 0)
			return false;
		return true;
	}

}
