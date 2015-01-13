package com.edu.agh.student.lakeproject.neuralnetwork;

import com.edu.agh.student.lakeproject.neuralnetwork.transitionfunction.*;

public class NeuralNetwork{

  private NeuralNetworkLayer[] layers;
  

  public NeuralNetwork(int inLayerWidth, int outLayerWidth, int hiddenLayerWidth, int hiddenLayersNumber, TransitionFunction function, boolean hasBias){
    layers = new NeuralNetworkLayer[hiddenLayersNumber];
    
    layers[0] = new NeuralNetworkLayer(inLayerWidth, hiddenLayerWidth, function, hasBias);
  
    for(int i = 1; i<layers.length-1; ++i){
      layers[i] = new NeuralNetworkLayer(hiddenLayerWidth, hiddenLayerWidth, function, hasBias);
    }
    
    layers[layers.length-1] = new NeuralNetworkLayer(hiddenLayerWidth, outLayerWidth, function, hasBias);
  
  }
  
  public NeuralNetwork(int inLayerWidth, int outLayerWidth, TransitionFunction function){
    this(inLayerWidth, outLayerWidth, 5, 3, function, true);
  }
  
  public NeuralNetwork(double[][][] weights, TransitionFunction transitionFunction, boolean hasBias){
    layers = new NeuralNetworkLayer[weights.length];
    for(int i=0; i<weights.length; ++i){
      layers[i] = new NeuralNetworkLayer(weights[i], transitionFunction, hasBias);
    }
  }
  
  public double[] proceed(double[] in){
    for(int i = 0; i<layers.length; ++i){
      in = layers[i].proceed(in);
    }
    
    return in;
  }
  
  public double[] affect(double[] in, int factor){
    for(int i = 0; i<layers.length; ++i){
      in = layers[i].affect(in, factor*0.0001f);
    }
    
    return in;
  }
  
  public double[] affect(double[] in, float factor){
    for(int i = 0; i<layers.length; ++i){
      in = layers[i].affect(in, factor);
    }
    
    return in;
  }
  
  public double[][][] getWeights(){
    double[][][] copy = new double[layers.length][][];
    
    for(int i = 0; i<layers.length; ++i){
      copy[i] = layers[i].getWeights();
    }
    
    return copy;
  }

public boolean hasBias() {
//	for(NeuralNetworkLayer neuralNetworkLayer: layers){
//		if(neuralNetworkLayer.hasBias)
//			return true;
//	}
//	return false;
	return true;
}
  
}
