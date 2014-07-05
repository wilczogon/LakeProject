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
		body.applyAngularImpulse((LakeConfiguration.random.nextFloat() -0.5f)*strength);
		body.applyForceToCenter(new Vec2((LakeConfiguration.random.nextFloat() -0.5f)*strength, (LakeConfiguration.random.nextFloat() -0.5f)*strength));
	}

}
