package com.edu.agh.student.lakeproject.fish.neuralnetworkbrainfish;

import java.awt.Color;
import java.util.*;

import com.edu.agh.student.lakeproject.fish.Brain;
import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.MovementDecision;
import com.edu.agh.student.lakeproject.neuralnetwork.NeuralNetwork;
import com.edu.agh.student.lakeproject.neuralnetwork.transitionfunction.*;

public class NeuralNetworkBrain extends Brain {

	private NeuralNetwork neuralNetwork = null;
	
	public NeuralNetworkBrain(){
		this(10, 3, 5);
	}
	
	public NeuralNetworkBrain(int inLayerWidth, int hiddenLayerNo, int hiddenLayerWidth){
		neuralNetwork = new NeuralNetwork(inLayerWidth, 2, hiddenLayerWidth, hiddenLayerNo, new PartiallyLinearTransitionFunction(), true);
	}
	
	public NeuralNetworkBrain(double[][][] weights){
	  this(weights, true);
	}
	
	public NeuralNetworkBrain(double[][][] weights, boolean hasBias){
	  neuralNetwork = new NeuralNetwork(weights, new PartiallyLinearTransitionFunction(), hasBias);
	}
	
	public MovementDecision decideMovement(EyeView eyeView){
	  Color[] colors = Arrays.copyOf(eyeView.getPixels(), eyeView.getPixels().length, Color[].class);
	  double[] values = new double[colors.length];
	  
	  for(int i = 0; i<colors.length; ++i)
	    values[i] = (colors[i].getRed()/255)*256*256 + (colors[i].getGreen()/255)*256 + (colors[i].getBlue()/255);	//TODO
	  
	  double[] result = neuralNetwork.proceed(values);
	  
	  MovementDecision decision = new MovementDecision((result[0]+1)/2, result[1]);	//the result is between 0 and 1 or -1 and 1
	  
	  return decision;
	}
	
	public double[][][] getBrainWeightsCopy(){
	  return neuralNetwork.getWeights();
	}

	public boolean hasBias() {
		return neuralNetwork.hasBias();
	}

}
