package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JPanel;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import com.edu.agh.student.lakeproject.fish.Fish;


public class LakeWorld extends World {
	
	private GraphicSystem graphicSystem;
	private JPanel panel;

	public LakeWorld(){
		super(new Vec2(0, 0));
//		//frame = new JFrame(LakeConfiguration.title);
//		panel = new JPanel();
//		panel.setBounds(10, 10, LakeConfiguration.width, LakeConfiguration.height);
	}
	
	public JPanel getFrame() {
		return panel;
	}

	public void setFrame(JPanel frame) {
		this.panel = frame;
	}

	public void start(){
		init();
	}
	
	public void step(){
		
		for(int i = 0; i<lakeObjects.size(); ++i){
			if(!lakeObjects.get(i).isActive()){
				super.destroyBody(lakeObjects.get(i).body);
				lakeObjects.remove(i);
			}
		}
		
		for(LakeObject lakeObject: lakeObjects){
			lakeObject.move();
		}
		super.step(LakeConfiguration.stepTime, 1, 1);
		
		if(getContactCount() != 0){
			Contact contact = getContactList();
			
			LakeObject objectA;
			LakeObject objectB;
			
			do{
				objectA = (LakeObject)contact.getFixtureA().m_body.getUserData();
				objectB = (LakeObject)contact.getFixtureB().m_body.getUserData();
				
				objectA.interactWith(objectB);
				objectB.interactWith(objectA);
				
				contact = contact.m_next;
			}while(contact != null);
		}
		
		//super.drawDebugData();
		graphicSystem.paint();
		graphicSystem.swapBuffers();
		//debugDraw.swapBuffers();
	}
	
	private void init(){
		this.graphicSystem = new GraphicSystem(this, panel); // TODO tymczasowo pole
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel.setVisible(true);
		
		timer = new Timer();
		timer.schedule(new LakeTimerTask(this), new Date(), (long)Math.floor(1000*LakeConfiguration.stepTime));
	}
	
	public void addMouseListener(MouseListener listener){
		panel.addMouseListener(listener);
	}
	
	public void addMouseMotionListener(MouseMotionListener listener){
		panel.addMouseMotionListener(listener);
	}

	public void addLakeObject(LakeObject lakeObject){
		
		
		System.out.println(lakeObject.toString());
		BodyDef bodyDef = new BodyDef();
		bodyDef.position = lakeObject.getInitialPosition();
		
		if(lakeObject.getType().equals(LakeConfiguration.fishTypeName))
			bodyDef.type = BodyType.DYNAMIC;
		else
			bodyDef.type = BodyType.STATIC;
		
		bodyDef.userData = lakeObject;
		Body body = super.createBody(bodyDef);
		lakeObject.setBody(body);
		lakeObjects.add(lakeObject);
	}
	
	public List<LakeObject> getLakeObjects(){
		return lakeObjects;
	}
	
	private Timer timer = null;
	private List<LakeObject> lakeObjects = new ArrayList<LakeObject>();
	@SuppressWarnings("unused")
	private List<Fish> retainers = new ArrayList<Fish>();
}
