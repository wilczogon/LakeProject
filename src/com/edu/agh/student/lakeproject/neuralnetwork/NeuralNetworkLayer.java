package com.edu.agh.student.lakeproject.neuralnetwork;

import java.util.Random;
import com.edu.agh.student.lakeproject.neuralnetwork.transitionfunction.*;


public class NeuralNetworkLayer{	//TODO it would be nice to allow user choose bounds of random weights

  double[][] weights = null;
  boolean hasBias = false;
  TransitionFunction function = null;
  
  public NeuralNetworkLayer(int inWidth, int outWidth, TransitionFunction function, boolean hasBias){
    this.function = function;
    this.hasBias = hasBias;
    
    Random random = new Random();
    
    if(hasBias)
      weights = new double[outWidth][inWidth+1];
    else
      weights = new double[outWidth][inWidth];
      
    for(double[] line: weights)
      for(int i = 0; i<line.length; ++i){
	line[i] = random.nextDouble()-0.5;
      }
  }
  
  public NeuralNetworkLayer(double[][] weights, TransitionFunction function, boolean hasBias){
    this.weights = weights;
    this.hasBias = hasBias;
    this.function = function;
  }
  
  public double[][] getWeights(){	// It will return copies of weights!
    double[][] copy = new double[weights.length][weights[0].length];
    
    for(int i = 0; i<weights.length; ++i)
      for(int j = 0; j<weights[0].length; ++j)
	copy[i][j] = weights[i][j];
    
    return copy;
  }
  
  private int getTrueInputWidth(){
      return weights[0].length;
  }
  
  public int getInputWidth(){
    if(hasBias)
      return getTrueInputWidth()-1;
    else
      return getTrueInputWidth();
  }
  
  public int getOutputWidth(){
      return weights.length;
  }
  
  public boolean hasBias(){
    return hasBias;
  }
  
  public double[] proceed(double[] in){
    if(hasBias){
      double[] tmp = new double[in.length+1];
      
      for(int i = 0; i<in.length; ++i)
	tmp[i] = in[i];

      tmp[in.length] = 1;
      in = tmp;
    }
    
    double[] out = new double[getOutputWidth()];
    
    for(int j = 0; j<getOutputWidth(); ++j){
      out[j] = 0;
      for(int i = 0; i<getTrueInputWidth(); ++i){
	out[j] += in[i]*weights[j][i];
      }
      
      out[j] = function.proceed(out[j]);
    }
    
    return out;
    
  }
  

} 
