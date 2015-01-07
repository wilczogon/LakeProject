package example;

import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFish;
import com.edu.agh.student.lakeproject.fish.Gender;
import org.jbox2d.common.Vec2;
import com.edu.agh.student.lakeproject.lakeworld.*;
import java.awt.Color;
import java.io.*;

public class ExampleFish extends NeuralNetworkBrainFish{
  public ExampleFish(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
    super(lakeWorld, in);
  }

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
}
