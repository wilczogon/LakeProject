package com.edu.agh.student.lakeproject.fish;

import java.io.Serializable;

public abstract class Brain implements Serializable{
	
	public Brain(){
		
	}
	
	public abstract MovementDecision decideMovement(EyeView eyeView);

}
