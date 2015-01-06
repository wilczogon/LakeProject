package example;

import com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish.NeuralNetworkBrainFish;
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
  }
}
