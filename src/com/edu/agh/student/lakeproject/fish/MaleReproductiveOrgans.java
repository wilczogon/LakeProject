package com.edu.agh.student.lakeproject.fish;

public class MaleReproductiveOrgans extends ReproductiveOrgans{
  public MaleReproductiveOrgans(){
    gender = Gender.MALE;
  }
  
  public Chromosome getChromosome(){
    return null;
  }
  
  @Override
  public void step(){
    if(!isMature()){
      --maturityCounter;
    }
  }
}