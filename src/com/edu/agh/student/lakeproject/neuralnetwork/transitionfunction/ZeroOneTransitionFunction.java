package com.edu.agh.student.lakeproject.neuralnetwork.transitionfunction;

public class ZeroOneTransitionFunction extends TransitionFunction{

  public double proceed(double value){
    if(value <=0)
      return 0.0;
    else
      return 1.0;
  }
  
}
