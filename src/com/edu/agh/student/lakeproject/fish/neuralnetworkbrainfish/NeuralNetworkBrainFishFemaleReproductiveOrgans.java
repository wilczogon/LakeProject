package com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish;

import com.edu.agh.student.lakeproject.fish.FemaleReproductiveOrgans;
import com.edu.agh.student.lakeproject.fish.Chromosome;
import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.lakeworld.*;

import org.jbox2d.common.Vec2;

public class NeuralNetworkBrainFishFemaleReproductiveOrgans extends FemaleReproductiveOrgans{
  
  public NeuralNetworkBrainFishFemaleReproductiveOrgans(LakeWorld lakeWorld, Fish ownerFish){
    super(lakeWorld, ownerFish);
  }
  
  @Override
  public void cross(Chromosome chromosome){
    double angle = LakeConfiguration.random.nextDouble()*2*Math.PI;
    double[][][] mWeights = ((NeuralNetworkBrainFishChromosome)chromosome).getWeights();
    double[][][] fWeights = ((NeuralNetworkBrainFish)ownerFish).getBrainWeightsCopy();
    
    for(int i=0; i<mWeights.length; ++i)
      for(int j=0; j<mWeights[i].length; ++j)
	for(int k=0; k<mWeights[i][j].length; ++k){
	
	  if(j<(mWeights[i].length/2))
	    mWeights[i][j][k] = fWeights[i][j][k];
	    
	  if(LakeConfiguration.random.nextInt()%3 == 0)
	    mWeights[i][j][k] += (LakeConfiguration.random.nextDouble() - 0.5); //mutation
	}
	
    super.children.add(new NeuralNetworkBrainFish(super.lakeWorld, mWeights, new Vec2((float)(ownerFish.getPosition().x+10*Math.cos(angle)), (float)(ownerFish.getPosition().y+10*Math.sin(angle)))));
  }
}
