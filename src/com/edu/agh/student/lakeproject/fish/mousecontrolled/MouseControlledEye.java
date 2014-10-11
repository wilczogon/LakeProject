package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import com.edu.agh.student.lakeproject.fish.Eye;
import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.lakeworld.LakeWorld;

public class MouseControlledEye extends Eye {

	MouseControlledFish fish;
	
	public MouseControlledEye(MouseControlledFish fish) {
		super();
		this.fish = fish;
	}

	@Override
	public EyeView getView() {
		return null;
	}

}
