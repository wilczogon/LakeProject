package com.edu.agh.student.lakeproject.lakeworld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JFrame;

import lessur.engine.physics.Slick2dDebugDraw;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import com.edu.agh.student.lakeproject.fish.veiltail.Veiltail;


public class LakeWorld extends World {
	
	public LakeWorld(){
		super(new Vec2(0, 0));
	}
	
	public void start(){
		init();
	}
	
	public void step(){
		for(LakeObject lakeObject: lakeObjects){
			lakeObject.move();
		}
		super.step(LakeConfiguration.stepTime, 1, 1);
		
		super.drawDebugData();
		debugDraw.swapBuffers();
	}
	
	private void init(){
		JFrame frame = new JFrame(LakeConfiguration.title);
		frame.setBounds(10, 10, LakeConfiguration.width, LakeConfiguration.height);
		
		debugDraw = new Slick2dDebugDraw(frame);
		debugDraw.setFlags(0x0001);
		super.setDebugDraw(debugDraw);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		timer = new Timer();
		timer.schedule(new LakeTimerTask(this), new Date(), (long)Math.floor(100*LakeConfiguration.stepTime));
	}

	public void addLakeObject(LakeObject lakeObject){
		
		
		System.out.println(lakeObject.toString());
		BodyDef bodyDef = new BodyDef();
		bodyDef.position = lakeObject.getInitialPosition();
		bodyDef.type = BodyType.DYNAMIC;
		Body body = super.createBody(bodyDef);
		lakeObject.setBody(body);
		lakeObjects.add(lakeObject);
		
		FixtureDef fixDef = new FixtureDef();
		CircleShape circle = new CircleShape();
		circle.m_radius = lakeObject.getRadius();
		fixDef.shape = circle;
		body.createFixture(fixDef);
	}
	
	private Timer timer = null;
	private Slick2dDebugDraw debugDraw;
	private List<LakeObject> lakeObjects = new ArrayList<LakeObject>();
}
