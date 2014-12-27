 package com.edu.agh.student.lakeproject.fish;
 
 import java.util.*;
 import org.jbox2d.common.Vec2;
 import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;
 import com.edu.agh.student.lakeproject.lakeworld.LakeObject;
 import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class FemaleReproductiveOrgans extends ReproductiveOrgans{
  private List<Fish> children = new ArrayList<Fish>();
  private LakeWorld lakeWorld;
  private Fish ownerFish;

  public FemaleReproductiveOrgans(LakeWorld lakeWorld, Fish ownerFish){
    gender = Gender.FEMALE;
    this.lakeWorld = lakeWorld;
    this.ownerFish = ownerFish;
  }
  
  public void cross(Chromosome chromosome){
    children.add(new Veiltail(lakeWorld, new Vec2(10, 10)));
  }
  
  public void giveBirth(){
    for(LakeObject child: children)
      lakeWorld.addLakeObject(child);
      
    children = new ArrayList<Fish>();
  }
  
  public void doReproduction(Chromosome chromosome){
    cross(chromosome);
    giveBirth();
  }
}