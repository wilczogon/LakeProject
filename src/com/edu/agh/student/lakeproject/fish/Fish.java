package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.common.Vec2;
import java.io.*;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;
import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;

import java.awt.Color;
import java.io.Serializable;

public abstract class Fish extends LakeObject implements Serializable{

	protected Eye eye;
	protected Brain brain;
	protected Muscles muscles;
	protected ReproductiveOrgans reproductiveOrgans;
	protected final int MAX_ENERGY = 500;
	protected final int MAX_HEALTH = 500;
	protected final float INITIAL_RADIUS = 10.0f;
	protected final float MAX_RADIUS = 20.0f;
	protected int energy = MAX_ENERGY/2;	// does fish eat something?
	protected int health = MAX_HEALTH;	// does fish fight with others?
	protected int age = 0;
	protected final float GROWTH_FACTOR = 0.001f;
	
	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Fish(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
	  super(lakeWorld, in);
	  energy = in.readInt();
	  health = in.readInt();
	  age = in.readInt();
	  Gender gender = (Gender)in.readObject();
	  
	  if(gender == Gender.MALE)
	    setImage("rsc/ryba-m.png");
	  else if(gender == Gender.FEMALE)
	    setImage("rsc/ryba-f.png");
	  else
	    setImage("rsc/ryba.png");
	  
	  initReproductionOrgans(gender);
	}
	
	public Fish(LakeWorld lakeWorld, Vec2 position, Gender gender, Color color){
		super(lakeWorld, 20.0f, position, gender == Gender.MALE ? new Color(color.getRed(), color.getGreen(), Math.min(color.getBlue()+20, 255)):color);	//Thanks to this males are little different in color than females
		radius = INITIAL_RADIUS;
		
		if(gender == Gender.MALE)
		  setImage("rsc/ryba-m.png");
		else if(gender == Gender.FEMALE)
		  setImage("rsc/ryba-f.png");
		else
		  setImage("rsc/ryba.png");
		
		initReproductionOrgans(gender);
	}
	
	public Fish(LakeWorld lakeWorld, Vec2 position, Color color){
		this(lakeWorld, position, (LakeConfiguration.random.nextInt()%2 == 0 ? Gender.FEMALE : Gender.MALE), color);
	}
	
	public void initReproductionOrgans(Gender gender){
	  if(gender == Gender.FEMALE)
	    reproductiveOrgans = new FemaleReproductiveOrgans(super.lakeWorld, this);
	  else
	    reproductiveOrgans = new MaleReproductiveOrgans();
	}
	
	@Override
	public String getType() {
		return LakeConfiguration.fishTypeName;
	}
	
	public ReproductiveOrgans getReproductiveOrgans(){
	  return reproductiveOrgans;
	}
	
	public Gender getGender(){
	  return reproductiveOrgans.getGender();
	}
	
	public String getSpecies(){
		return this.getClass().getName();
	}
	
	@Override
	public void move(){
		//System.out.println(this.getSpecies());
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(GROWTH_FACTOR*age + 1) + MAX_RADIUS);
		energy--;
		if(energy > MAX_ENERGY/2)
		  health = Math.min(health+1, MAX_HEALTH);
		getReproductiveOrgans().step();
		muscles.applyForces(brain.decideMovement(eye.getView()));
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
		if(fish.getSpecies().equals(getSpecies())){
		  if(getGender() != fish.getGender()){
			if(getGender() == Gender.MALE && getReproductiveOrgans().isMature()){
			 ((FemaleReproductiveOrgans)fish.getReproductiveOrgans()).doReproduction(((MaleReproductiveOrgans)getReproductiveOrgans()).getChromosome());
			}
		  } else{
			health--;
		  }
		  
		} else{
		  health-=20;
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

	public int getMaxEnergy() {
		return MAX_ENERGY;
	}

	public int getMaxHealth() {
		return MAX_HEALTH;
	}
	
	@Override
	public void writeToStream(ObjectOutputStream out) throws IOException{
	  super.writeToStream(out);
	  out.writeInt(energy);
	  out.writeInt(health);
	  out.writeInt(age);
	  out.writeObject(reproductiveOrgans.getGender());
	}
	
	public void reset(){
	  energy = MAX_ENERGY/2;
	  health = MAX_HEALTH;
	}

}
