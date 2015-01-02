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
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new Muscles(super.body);
		super.brain = new NeuralNetworkBrain();
		super.eye = new Eye(super.getLakeWorld(), this);
	}
	
	/*public void initReproductionOrgans(Gender gender){
	  //TODO soon
	}*/
	
	public String getSpace(){
	  return "com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish";
	}
	
	@Override
	public void move(){
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(growthFactor*age + 1) + MAX_RADIUS);
		energy--;
		muscles.applyForces(brain.decideMovement(eye.getView()));
	}	

}
