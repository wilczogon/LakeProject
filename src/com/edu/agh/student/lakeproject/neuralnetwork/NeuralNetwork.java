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
  
  public double[] proceed(double[] in){
    for(int i = 0; i<layers.length; ++i){
      in = layers[i].proceed(in);
    }
    
    return in;
  }
  
}
