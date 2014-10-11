package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.common.Vec2;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;
import java.awt.Color;

public abstract class Fish extends LakeObject {

	protected Eye eye;
	protected Brain brain;
	protected Muscles muscles;
	protected ReproductiveOrgans reproductiveOrgans;
	protected final int MAX_ENERGY = 1000;
	protected final int MAX_HEALTH = 1000;
	protected final float INITIAL_RADIUS = 10.0f;
	protected final float MAX_RADIUS = 50.0f;
	protected int energy = MAX_ENERGY;	// does fish eat something?
	protected int health = MAX_HEALTH;	// does fish fight with others?
	protected int age = 0;
	protected float growthFactor = 0.001f;
	
	public Fish(LakeWorld lakeWorld, Vec2 position/*, Gender gender*/, Color color){
		super(lakeWorld, 20.0f, position, color);
		radius = INITIAL_RADIUS;
		//initReproductionOrgans(gender); TODO
	}
	
	//public Fish(LakeWorld lakeWorld, Vec2 position){
	//	this(lakeWorld, 20.0f, position, null);//, (Math.random()%2 == 0 ? new Gender.FEMALE() : new Gender.MALE()));
	//}
	
	public void initReproductionOrgans(Gender gender){
	  /*if(gender == Gender.FEMALE)
	    reproductiveOrgans = new FemaleReproductiveOrgans();
	  else if (gender == Gender.MALE)
	    reproductiveOrgans = new MaleReproductiveOrgans();*/
	}
	
	@Override
	public String getType() {
		return LakeConfiguration.fishTypeName;
	}
	
	public Gender getGender(){
	  return reproductiveOrgans.getGender();
	}
	
	public abstract String getSpace();
	
	@Override
	public void move(){
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(growthFactor*age + 1) + MAX_RADIUS);
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
		if(fish.getSpace().equals(getSpace()) ){//&& getGender() != fish.getGender()){ TODO
			// mój gatunek, przeciwna płeć
		} else{
			health--;
		}
	}
	
	protected void interactWith(Obstacle obstacle){
		health--;
	}
	
	protected void interactWith(Food food){
		energy+= food.getEnergy();
		if(energy > MAX_ENERGY)
		  energy = MAX_ENERGY;
	}
	
	@Override
	public boolean isActive(){
		if(health <= 0 || energy <= 0)
			return false;
		return true;
	}

}
