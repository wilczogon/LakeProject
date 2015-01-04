package com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.*;
import com.edu.agh.student.lakeproject.fish.veiltail.*;
import com.edu.agh.student.lakeproject.lakeworld.*;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.food.Food;
import java.awt.Color;

public class NeuralNetworkBrainFish extends Fish {

	public NeuralNetworkBrainFish(LakeWorld lakeWorld, Vec2 position){
		super(lakeWorld, position, Color.RED);
	}
	
	public NeuralNetworkBrainFish(LakeWorld lakeWorld, double[][][] weights, Vec2 position){
		super(lakeWorld, position, Color.RED);
		super.brain = new NeuralNetworkBrain(weights);
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new Muscles(super.body);
		if(super.brain == null)
		  super.brain = new NeuralNetworkBrain();
		super.eye = new Eye(super.getLakeWorld(), this);
	}
	
	public void initReproductionOrgans(Gender gender){
	  if(gender == Gender.FEMALE)
	    super.reproductiveOrgans = new NeuralNetworkBrainFishFemaleReproductiveOrgans(super.lakeWorld, this);
	  else
	    super.reproductiveOrgans = new NeuralNetworkBrainFishMaleReproductiveOrgans(this);
	}
	
	public String getSpace(){
	  return "com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish";
	}
	
	public double[][][] getBrainWeightsCopy(){
	  return ((NeuralNetworkBrain)super.brain).getBrainWeightsCopy();
	}

}
