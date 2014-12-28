package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JPanel;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import com.edu.agh.student.lakeproject.fish.Fish;
import com.edu.agh.student.lakeproject.userinterface.LakeObjectFocusListener;


public class LakeWorld extends World implements MouseListener{
	
	private GraphicSystem graphicSystem;
	private JPanel panel;
	private ReportManager reportManager;

	public ReportManager getReportManager() {
		return reportManager;
	}

	public LakeWorld(){
		super(new Vec2(0, 0));
		lakeObjectFocusListeners = new ArrayList<LakeObjectFocusListener>();
		reportManager = new ReportManager(this);
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
		
		for(int i = 0; i<lakeObjects.size(); ++i)
			lakeObjects.get(i).move();
		
		super.step(LakeConfiguration.stepTime, 1, 1);
		
		if(getContactCount() != 0){
			Contact contact = getContactList();
			
			LakeObject objectA;
			LakeObject objectB;
			
			do{
				if(contact.getFixtureA().m_body.getUserData() != null && contact.getFixtureB().m_body.getUserData() != null){
				  objectA = (LakeObject)contact.getFixtureA().m_body.getUserData();
				  objectB = (LakeObject)contact.getFixtureB().m_body.getUserData();
				
				  objectA.interactWith(objectB);
				  objectB.interactWith(objectA);
				}
				
				contact = contact.m_next;
			}while(contact != null);
		}
		
		//super.drawDebugData();
		graphicSystem.paint();
		graphicSystem.swapBuffers();
		//debugDraw.swapBuffers();
		reportManager.step();
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
	
	public void addBound(Vec2 a, Vec2 b, Vec2 c, Vec2 d){
	  BodyDef bodyDef = new BodyDef();
	  bodyDef.position = new Vec2(0, 0);
	  bodyDef.type = BodyType.STATIC;
	  Body body = super.createBody(bodyDef);
	  FixtureDef fixDef = new FixtureDef();
	  Vec2[] vertices = new Vec2[]{a, b, c, d};
	  PolygonShape shape = new PolygonShape();
	  shape.set(vertices, vertices.length);
	  fixDef.shape = shape;
	  body.createFixture(fixDef);
	}
	
	public List<LakeObject> getLakeObjects(){
		return lakeObjects;
	}
	
	public GraphicSystem getGraphicSystem(){
	  return graphicSystem;
	}
	
	private Timer timer = null;
	private List<LakeObject> lakeObjects = new ArrayList<LakeObject>();
	@SuppressWarnings("unused")
	private List<Fish> retainers = new ArrayList<Fish>();
	private List<LakeObjectFocusListener> lakeObjectFocusListeners;

	public void addLakeObjectFocusListener(LakeObjectFocusListener listener) {
		lakeObjectFocusListeners.add(listener);
		
	}
	
	private void setChosenLakeObject(LakeObject chosen){
		for(LakeObjectFocusListener listener: lakeObjectFocusListeners){
			listener.setChosenLakeObject(chosen);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
		Vec2 point = new Vec2(arg0.getX(), arg0.getY()); 
		
		for(LakeObject lakeObject :lakeObjects){
			if(lakeObject instanceof Fish && lakeObject.fixture.testPoint(point)){
				setChosenLakeObject(lakeObject);
				break;
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
