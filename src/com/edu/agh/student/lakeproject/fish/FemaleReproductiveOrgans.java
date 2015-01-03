 package com.edu.agh.student.lakeproject.fish;
 
 import java.util.*;
 import org.jbox2d.common.Vec2;
 import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
 import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
 import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;
 import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

public class FemaleReproductiveOrgans extends ReproductiveOrgans{
  protected List<Fish> children = new ArrayList<Fish>();
  protected LakeWorld lakeWorld;
  protected Fish ownerFish;
  protected final static int PREGNANCY_DURATION = 100;
  protected final static int RECOVERY_DURATION = 100;
  private int pregnancyCounter = 0;
  private int recoveryCounter = 0;

  public FemaleReproductiveOrgans(LakeWorld lakeWorld, Fish ownerFish){
    gender = Gender.FEMALE;
    this.lakeWorld = lakeWorld;
    this.ownerFish = ownerFish;
  }
  
  @Override
  public void step(){
    if(!isMature()){
      --maturityCounter;
    }else if(isPregnant()){
      --pregnancyCounter;
      if(pregnancyCounter == 0)
	giveBirth();
    } else if(isRecovering()){
      --recoveryCounter;
    }
  }
  
  public boolean isPregnant(){
    if(pregnancyCounter == 0)
      return false;
    else
      return true;
  }
  
  public boolean isRecovering(){
    if(recoveryCounter == 0)
      return false;
    else
      return true;
  }
  
  public void cross(Chromosome chromosome){	// in this function you should put right Fishes into list "children"
    double angle = LakeConfiguration.random.nextDouble()*2*Math.PI;
    children.add(new Veiltail(lakeWorld, new Vec2((float)(ownerFish.getPosition().x+10*Math.cos(angle)), (float)(ownerFish.getPosition().y+10*Math.sin(angle)))));
  }
  
  public void giveBirth(){
    for(LakeObject child: children)
      lakeWorld.addLakeObject(child);
      
    children = new ArrayList<Fish>();
    recoveryCounter = RECOVERY_DURATION;
  }
  
  public void doReproduction(Chromosome chromosome){
    if(!isPregnant() && !isRecovering() && isMature() && ownerFish.getEnergy()>ownerFish.MAX_ENERGY/2){
      cross(chromosome);
      pregnancyCounter = PREGNANCY_DURATION;
    }
  }
}