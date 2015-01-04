package com.edu.agh.student.lakeproject.fish;

import java.io.Serializable;

public class Brain implements Serializable{
	
	public Brain(){
		
	}
	
	public MovementDecision decideMovement(EyeView eyeView){ //this is brain to use, when you don't want to use it - see Veiltail
	  return null;
	}

}
