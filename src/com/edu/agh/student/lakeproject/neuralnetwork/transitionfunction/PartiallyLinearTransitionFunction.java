package com.edu.agh.student.lakeproject.neuralnetwork.transitionfunction;

public class PartiallyLinearTransitionFunction extends TransitionFunction{

  public double proceed(double value){
    if(value > 1)
      return 1;
    else if(value < -1)
      return -1;
    else
      return value;
  }

}
 
