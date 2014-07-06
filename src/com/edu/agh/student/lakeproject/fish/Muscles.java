package com.edu.agh.student.lakeproject.fish;

import org.jbox2d.dynamics.Body;

public abstract class Muscles {
	
	protected Body body;
	protected float strength; //siła przekładająca się na szybkość
	protected float agility; //zwrotność

	public Muscles(Body body){
		this.body = body;
	}

	public abstract void applyForces(MovementDecision decision);
}
