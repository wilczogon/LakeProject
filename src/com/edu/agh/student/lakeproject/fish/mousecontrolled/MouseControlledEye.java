package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import com.edu.agh.student.lakeproject.fish.Eye;
import com.edu.agh.student.lakeproject.fish.EyeView;

public class MouseControlledEye extends Eye {

	MouseControlledFish fish;
	
	public MouseControlledEye(MouseControlledFish fish) {
		super();
		this.fish = fish;
	}

	@Override
	public EyeView getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
