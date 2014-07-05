package com.edu.agh.student.lakeproject.lakeworld;

import java.util.Random;

public class LakeConfiguration {
	public final static String title = "Lake Project";
	public final static float stepTime = 1/36f; 
	
	public final static int width = 600;
	public final static int height = 500;
	
	public final static String fishTypeName = "Fish";
	public final static String obstacleTypeName = "Obstacle";
	public final static String foodTypeName = "Food";
	
	public final static int wallWidth = 10;
	
	public static Random random = new Random();
	
	public static int getLeftWallPosition(){
		return -(int)Math.floor(width/2);
	}
	
	public static int getRightWallPosition(){
		return (int)Math.floor(width/2);
	}
	
	public static int getTopWallPosition(){
		return (int)Math.floor(height/2);
	}
	
	public static int getBottomWallPosition(){
		return -(int)Math.floor(height/2);
	}
}
