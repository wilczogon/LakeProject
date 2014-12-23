package com.edu.agh.student.lakeproject.fish;

public class MovementDecision {
  private double speed;
  private double angle;
  
  public MovementDecision(double speed, double angle){
    this.speed = speed;
    this.angle = angle;
  }
  
  public double getSpeed(){
    return speed;
  }
  
  public double getAngle(){
    return angle;
  }

}
