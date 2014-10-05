 package com.edu.agh.student.lakeproject.fish;

public abstract class FemaleReproductiveOrgans extends ReproductiveOrgans{
  public FemaleReproductiveOrgans(){
    gender = Gender.FEMALE;
  }
  
  public abstract void cross(Chromosome chromosome);
  
  public abstract void giveBirth();
}