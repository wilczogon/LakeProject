package com.edu.agh.student.lakeproject.fish.impl;

import java.awt.Color;
import java.util.ArrayList;

import com.edu.agh.student.lakeproject.fish.EyeView;

public class ArrayEyeView extends EyeView {

	private ArrayList<Color> viewArray;
	public ArrayEyeView(int viewSize) {
		this.viewArray = new ArrayList<Color>(viewSize);
	}

	public void appendPixel(Color pixel) {
		this.viewArray.add(pixel);
		
	}

	@Override
	public Object[] getPixels() {
		return this.viewArray.toArray();
	}

	@Override
	public void addPixel(Color color) {
		this.viewArray.add(color);
	}

}
