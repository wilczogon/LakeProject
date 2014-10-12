package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.Color;

import com.edu.agh.student.lakeproject.fish.Eye;
import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.lakeworld.GraphicSystem;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class MouseControlledEye extends Eye {

	private MouseControlledFish fish;
	private LakeWorld lakeWorld;
	
	public MouseControlledEye(MouseControlledFish fish, LakeWorld lakeWorld) {
		super();
		this.fish = fish;
		this.lakeWorld = lakeWorld;
	}

	@Override
	public EyeView getView() {
		EyeView result = lakeWorld.getGraphicSystem().getEyeView(fish, 5, 10);
//		for(Color color: result.getPixels()){
//			System.out.print(color.toString());
//		}
//		System.out.println();
		return result;
	}

}
