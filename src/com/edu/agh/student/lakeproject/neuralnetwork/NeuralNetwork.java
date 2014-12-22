


public class NeuralNetwork{

  private TransitionFunction function;
  

  public NeuralNetwork(int inLayerWidth, int outLayerWidth, int hiddenLayerWidth, int hiddenLayersNumber, TransitionFunction function){
  }
  
  public NeuralNetwork(int inLayerWidth, int outLayerWidth, TransitionFunction function){
    this(inLayerWidth, outLayerWidth, 5, 3, function);
  }
  
}
