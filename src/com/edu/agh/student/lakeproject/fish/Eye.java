package com.edu.agh.student.lakeproject.fish;

import java.awt.Color;
import java.io.Serializable;

import com.edu.agh.student.lakeproject.fish.Eye;
import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.lakeworld.GraphicSystem;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class Eye implements Serializable{

	private LakeWorld lakeWorld;
	private Fish fish;
	private int resolution = 10;
	private int radius = 100;

	public Eye(LakeWorld lakeWorld, Fish fish){
	  this(lakeWorld, fish, 10, 100);
	}
	
	public Eye(LakeWorld lakeWorld, Fish fish, int resolution){
	  this(lakeWorld, fish, resolution, 100);
	}
	
	public Eye(LakeWorld lakeWorld, Fish fish, int resolution, int angle){
	  this.lakeWorld = lakeWorld;
	  this.resolution = resolution;
	  this.fish = fish;
	  this.radius = angle;
	}
	
	public EyeView getView(){
	  EyeView result = lakeWorld.getGraphicSystem().getEyeView(fish, radius, resolution);
	  return result;
	}

}
