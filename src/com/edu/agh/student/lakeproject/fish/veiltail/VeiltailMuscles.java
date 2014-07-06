package com.edu.agh.student.lakeproject.fish.veiltail;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.MovementDecision;
import com.edu.agh.student.lakeproject.fish.Muscles;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

public class VeiltailMuscles extends Muscles {

	public VeiltailMuscles(Body body) {
		super(body);
		super.strength = 20;
		super.agility = 10;
	}

	@Override
	public void applyForces(MovementDecision decision) {//TODO
		float angle = (body.getAngle() + (LakeConfiguration.random.nextFloat() - 0.5f)*2*agility)%360;
		float speed = LakeConfiguration.random.nextFloat()*strength;
		body.m_sweep.a = angle;
		body.applyForceToCenter(new Vec2((float)Math.sin((angle/180)*Math.PI)*speed, -(float)Math.cos((angle/180)*Math.PI)*speed));
		System.out.println(angle + " " + speed);
		
	}

}
