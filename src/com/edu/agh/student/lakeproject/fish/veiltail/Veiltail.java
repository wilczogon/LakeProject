package com.edu.agh.student.lakeproject.fish.veiltail;

import java.awt.Color;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
import com.edu.agh.student.lakeproject.fish.Gender;

public class Veiltail extends Fish {
	
	public Veiltail(LakeWorld lakeWorld, Vec2 position){
		super(lakeWorld, position, Color.YELLOW);
	}
	
	public Veiltail(LakeWorld lakeWorld, Vec2 position, Gender gender){
		super(lakeWorld, position, gender, Color.YELLOW);
	}

	@Override
	public String getSpace() {
		return "com.edu.agh.student.lakeproject.fish.veiltail";
	}
	
	@Override
	public void setBody(Body body){
		super.setBody(body);
		super.muscles = new VeiltailMuscles(super.body);
	}
	
	@Override
	public void move(){
		age++;
		setRadius(INITIAL_RADIUS - MAX_RADIUS/(growthFactor*age + 1) + MAX_RADIUS);
		energy--;
		getReproductiveOrgans().step();
		muscles.applyForces(null); //Veiltail is stupid - no brain
	}

}
