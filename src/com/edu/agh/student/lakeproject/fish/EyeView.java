package com.edu.agh.student.lakeproject.fish;

import java.awt.Color;
import java.util.*;

public class EyeView {
	private List<Color> pixels = new ArrayList<Color>();

	public Object[] getPixels(){
	  return pixels.toArray(new Color[pixels.size()]);
	}
	
	public void addPixel(Color color){
	  pixels.add(color);
	}
}
