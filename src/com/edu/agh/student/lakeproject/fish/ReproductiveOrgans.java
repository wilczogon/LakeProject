package com.edu.agh.student.lakeproject.fish;

public abstract class ReproductiveOrgans{

  protected final static int MATURATION_DURATION = 100;
  protected int maturityCounter = MATURATION_DURATION;

  public ReproductiveOrgans(){}
  
  public Gender getGender(){
    return gender;
  }
  
  protected Gender gender;
  
  public abstract void step();
  
  public boolean isMature(){
    if(maturityCounter == 0)
      return true;
    else
      return false;
  }
}
