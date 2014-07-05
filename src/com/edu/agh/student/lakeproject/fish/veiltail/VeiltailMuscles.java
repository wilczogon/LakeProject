package com.edu.agh.student.lakeproject.fish.veiltail;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.MovementDecision;
import com.edu.agh.student.lakeproject.fish.Muscles;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

public class VeiltailMuscles extends Muscles {

	public VeiltailMuscles(Body body) {
		super(body);
		super.strength = 10;
	}

	@Override
	public void applyForces(MovementDecision decision) {//TODO
		body.applyForce(new Vec2((float)((LakeConfiguration.random.nextFloat() -0.5)*strength),  (float)((LakeConfiguration.random.nextFloat() -0.5)*strength)), new Vec2(0, 20));
	}

}
