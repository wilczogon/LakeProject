package com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish;

import com.edu.agh.student.lakeproject.fish.MaleReproductiveOrgans;
import com.edu.agh.student.lakeproject.fish.Chromosome;
import com.edu.agh.student.lakeproject.fish.Fish;

public class NeuralNetworkBrainFishMaleReproductiveOrgans extends MaleReproductiveOrgans{
  private Fish ownerFish;

  public NeuralNetworkBrainFishMaleReproductiveOrgans(Fish ownerFish){
    super();
    this.ownerFish = ownerFish;
  }

  public Chromosome getChromosome(){
    Chromosome chromosome = new NeuralNetworkBrainFishChromosome(((NeuralNetworkBrainFish)ownerFish).getBrainWeightsCopy());
    
    return chromosome;
  }
}

