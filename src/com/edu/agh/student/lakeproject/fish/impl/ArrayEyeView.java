package com.edu.agh.student.lakeproject.fish.impl;

import java.awt.Color;
import java.util.ArrayList;

import com.edu.agh.student.lakeproject.fish.EyeView;

public class ArrayEyeView extends EyeView {

	private ArrayList<Color> viewArray;
	public ArrayEyeView(int viewSize) {
		this.viewArray = new ArrayList(viewSize);
	}

	public void appendPixel(Color pixel) {
		this.viewArray.add(pixel);
		
	}

	@Override
	public Color[] getPixels() {
		return (Color[]) this.viewArray.toArray();
	}

	@Override
	public void addPixel(Color color) {
		// TODO Auto-generated method stub
		
	}

}
