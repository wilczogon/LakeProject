package com.edu.agh.student.lakeproject.fish.veiltail;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.MovementDecision;
import com.edu.agh.student.lakeproject.fish.Muscles;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

public class VeiltailMuscles extends Muscles {

	public VeiltailMuscles(Body body) {
		super(body);
		super.strength = 100;
	}

	@Override
	public void applyForces(MovementDecision decision) {//TODO
		Vec2 force = new Vec2((LakeConfiguration.random.nextFloat() -0.5f)*strength, (LakeConfiguration.random.nextFloat() -0.5f)*strength);
		Vec2 normal = new Vec2(0, 1);
		body.m_sweep.a = (float) (180/Math.PI*Math.acos((force.y*normal.y) / Math.sqrt((force.x*force.x + force.y*force.y))));
		System.out.println(body.m_sweep.a);
		body.applyForceToCenter(force);
	}

}
