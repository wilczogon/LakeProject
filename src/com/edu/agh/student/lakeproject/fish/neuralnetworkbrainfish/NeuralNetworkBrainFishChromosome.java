package com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish;

import com.edu.agh.student.lakeproject.fish.Chromosome;

public class NeuralNetworkBrainFishChromosome extends Chromosome{
  private double[][][] weights;
  
  public NeuralNetworkBrainFishChromosome(double[][][] weights){
    this.weights = weights;
  }
  
  public double[][][] getWeights(){
    return weights;
  }
}
