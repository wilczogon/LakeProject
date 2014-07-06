package com.edu.agh.student.lakeproject.fish.mousecontrolled;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import com.edu.agh.student.lakeproject.fish.MovementDecision;
import com.edu.agh.student.lakeproject.fish.Muscles;
import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

public class MouseControlledMuscles extends Muscles implements MouseListener {

	public MouseControlledMuscles(Body body) {
		super(body);
	}

	@Override
	public void applyForces(MovementDecision decision) {
		return;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		float x = arg0.getX() - body.getPosition().x;
		float y = arg0.getY() - body.getPosition().y;
		
		float angle = (float) ((180/Math.PI)*Math.acos(-y/Math.sqrt(x*x + y*y)));
		if(arg0.getX() < body.getPosition().x)
			angle *=(-1);
		float speed = (float) Math.sqrt((body.getPosition().x - arg0.getX())*(body.getPosition().x - arg0.getX()) +
				(body.getPosition().y - arg0.getY())*(body.getPosition().y - arg0.getY()));
		body.m_sweep.a = angle;
		
		body.applyForceToCenter(new Vec2((float)Math.sin((angle/180)*Math.PI)*speed, -(float)Math.cos((angle/180)*Math.PI)*speed));
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
