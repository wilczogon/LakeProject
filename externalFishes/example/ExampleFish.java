package example;

import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFish;
import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFishMaleReproductiveOrgans;
import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrain;
import com.edu.agh.student.lakeproject.fish.Gender;
import org.jbox2d.common.Vec2;
import com.edu.agh.student.lakeproject.lakeworld.*;
import java.awt.Color;
import java.io.*;

public class ExampleFish extends NeuralNetworkBrainFish{

  public ExampleFish(LakeWorld lakeWorld, Vec2 position){
    super(lakeWorld, position);
    super.color = Color.PINK;
    
    if(super.reproductiveOrgans.getGender() == Gender.MALE)
	setImage("rsc/ryba-m.png");
    else if(super.reproductiveOrgans.getGender() == Gender.FEMALE)
	setImage("rsc/ryba-f.png");
    else
	setImage("rsc/ryba.png");
  }
  
  public ExampleFish(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
	  super(lakeWorld, in);
	  
	  super.color = Color.PINK;
    
	  if(super.reproductiveOrgans.getGender() == Gender.MALE)
	    setImage("rsc/ryba-m.png");
	  else if(super.reproductiveOrgans.getGender() == Gender.FEMALE)
	    setImage("rsc/ryba-f.png");
	  else
	    setImage("rsc/ryba.png");
  }
  
  public ExampleFish(LakeWorld lakeWorld, double[][][] weights, Vec2 position){
	super(lakeWorld, position);
	
	super.color = Color.PINK;
    
	  if(super.reproductiveOrgans.getGender() == Gender.MALE)
	    setImage("rsc/ryba-m.png");
	  else if(super.reproductiveOrgans.getGender() == Gender.FEMALE)
	    setImage("rsc/ryba-f.png");
	  else
	    setImage("rsc/ryba.png");
	
	super.brain = new NeuralNetworkBrain(weights);
  }
  
  @Override
  public void initReproductionOrgans(Gender gender){
    if(gender == Gender.FEMALE)
      super.reproductiveOrgans = new ExampleFishFemaleReproductiveOrgans(super.lakeWorld, this);
    else
      super.reproductiveOrgans = new NeuralNetworkBrainFishMaleReproductiveOrgans(this);
    }
  
  @Override
  public void decreaseHealth(int health){
    super.health-=health;
    ((NeuralNetworkBrain)super.brain).affect(-health);
  }
  
  @Override
  public void increaseEnergy(int energy){
    super.energy+=energy;
    ((NeuralNetworkBrain)super.brain).affect(energy);
  }
}
