package com.edu.agh.student.lakeproject.fish;

public abstract class Brain {
	
	public Brain(){
		
	}
	
	public abstract MovementDecision decideMovement(EyeView eyeView);

}
