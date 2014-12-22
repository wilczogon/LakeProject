package com.edu.agh.student.lakeproject.neuralnetwork;

import java.util.Random;


public class NeuralNetworkLayer{

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
	line[i] = random.nextDouble();
      }
  }
  
  public NeuralNetworkLayer(double[][] weights, TransitionFunction function, boolean hasBias){
    this.weights = weights;
    this.hasBias = hasBias;
    this.function = function;
  }
  
  public double[][] getWeights(){
    return weights;
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
      //dodaj do in 1 na koncu
    }
    
    double[] out = new double[getOutputWidth()];
    
    for(int j = 0; j<getOutputWidth(); ++j){
      for(int i = 0; i<getTrueInputWidth(); ++i){
	out[j] += in[i]*weights[j][i];
      }
      
      out[j] = function.proceed(out[j]);
    }
    
    return out;
    
  }
  
  public static void main(String[] args){
    NeuralNetworkLayer layer = new NeuralNetworkLayer(3, 2, new ZeroOneTransitionFunction(), false);
    
    double[] in = new double[3];
    in[0] = 2.3;
    in[1] = 0.1;
    in[2] = 4.6;
    double[] out = layer.proceed(in);
    
    for(double value: out)
      System.out.println(value);
    
  }
  

} 
