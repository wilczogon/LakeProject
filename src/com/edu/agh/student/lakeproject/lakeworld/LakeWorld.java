package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
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
import com.edu.agh.student.lakeproject.food.Food;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.userinterface.LakeObjectFocusListener;


public class LakeWorld extends World implements MouseListener, MouseMotionListener{
	
	private GraphicSystem graphicSystem;
	private JPanel panel;
	private ReportManager reportManager;
	private Timer timer = null;
	private List<LakeObject> lakeObjects = new ArrayList<LakeObject>();
	@SuppressWarnings("unused")
	private List<Fish> retainers = new ArrayList<Fish>();
	private List<LakeObjectFocusListener> lakeObjectFocusListeners;
	private boolean isTimerStarted = false;
	private boolean isTimerForwarded = false;
	private float timerStep = 0;
	private LakeObject chosen;
	private int feedProbability = 20;
	private boolean recorded;
	private String recordDirectoryName;

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
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	public void start(){
		init();
	}
	
	public void step(){
	
		if(LakeConfiguration.random.nextInt() % 100 > feedProbability)
			addLakeObject(new Food(this, 10, getNewObjectPosition(), 100));
	
		List<LakeObject> deadFishes = new ArrayList<LakeObject>();
		
		for(int i = 0; i<lakeObjects.size(); ++i){
			if(!lakeObjects.get(i).isActive()){
				LakeObject object = lakeObjects.get(i);
				super.destroyBody(object.body);
				lakeObjects.remove(i);
				if(object instanceof Fish){
				  deadFishes.add(new Food(this, 10, object.getPosition(), 100));
				  reportManager.reportDeadFish((Fish)object);
				}
			}
		}
		
		for(LakeObject food: deadFishes)
		  addLakeObject(food);
		
		for(int i = 0; i<lakeObjects.size(); ++i)
			lakeObjects.get(i).move();
		
		super.step(timerStep, 1, 1);
		
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
		if(chosen!=null && !chosen.isActive())
			chosen = null;
		setChosenLakeObject(chosen);
	}
	
	private void init(){
		this.graphicSystem = new GraphicSystem(this, panel); // TODO tymczasowo pole
		this.addLakeObjectFocusListener(graphicSystem);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel.setVisible(true);
		
		timer = new Timer();
		startTimer();
	}
	
	public boolean isTimerStarted(){
	  return isTimerStarted;
	}
	
	public boolean isTimerForwarded(){
	  return isTimerForwarded;
	}
	
	public void stopTimer(){
	  if(isTimerStarted()){
	    timer.cancel();
	    timer.purge();
	    isTimerStarted = false;
	    isTimerForwarded = false;
	  }
	}
	
	public void startTimer(){
	  isTimerForwarded = false;
	  startTimer((long)Math.floor(2000*LakeConfiguration.stepTime));
	}
	
	public void forwardTimer(long milliseconds){
	  isTimerForwarded = true;
	  startTimer(milliseconds);
	}
	
	public void startTimer(long milliseconds){
	  stopTimer();
	  timer = new Timer();
	  timer.schedule(new LakeTimerTask(this), new Date(), milliseconds);
	  isTimerStarted = true;
	  timerStep = milliseconds;
	}
	
	public void addMouseListener(MouseListener listener){
		panel.addMouseListener(listener);
	}
	
	public void addMouseMotionListener(MouseMotionListener listener){
		panel.addMouseMotionListener(listener);
	}

	public void addLakeObject(LakeObject lakeObject){
		
		
		//System.out.println(lakeObject.toString());
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
	  LakeObject obstacle = new Obstacle(this, 0, new Vec2());
	  BodyDef bodyDef = new BodyDef();
	  bodyDef.position = new Vec2(0, 0);
	  bodyDef.type = BodyType.STATIC;
	  
	  bodyDef.userData = obstacle;
	  Body body = super.createBody(bodyDef);
	  
	  FixtureDef fixDef = new FixtureDef();
	  Vec2[] vertices = new Vec2[]{a, b, c, d};
	  PolygonShape shape = new PolygonShape();
	  shape.set(vertices, vertices.length);
	  fixDef.shape = shape;
	  body.createFixture(fixDef);
	  
	  obstacle.setBody(body);
	  lakeObjects.add(obstacle);
	  
	  //Body body = super.createBody(bodyDef);
	}
	
	public List<LakeObject> getLakeObjects(){
		return lakeObjects;
	}
	
	public GraphicSystem getGraphicSystem(){
	  return graphicSystem;
	}

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
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		Vec2 point = new Vec2(arg0.getX(), arg0.getY()); 
		
		for(LakeObject lakeObject :lakeObjects){
			if(lakeObject.fixture.testPoint(point)){
				chosen = lakeObject;
				setChosenLakeObject(lakeObject);
				break;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public Vec2 getNewObjectPosition(){	//it will tell you where you can place your new object... approximatelly
		Vec2 newObjectPosition = new Vec2(Math.abs(LakeConfiguration.random.nextInt())%(LakeConfiguration.width-20) + 10, Math.abs(LakeConfiguration.random.nextInt())%(LakeConfiguration.height-20) + 10);
		boolean cnt = true;
		for(int ii = 0; ii < 100; ii++){
			cnt = true;
			for(LakeObject lakeObject :lakeObjects){
				if(lakeObject.fixture.testPoint(newObjectPosition)){
					cnt = false;
					break;
				}
			}
			if(cnt){
				return newObjectPosition;
			}
			newObjectPosition = new Vec2(Math.abs(LakeConfiguration.random.nextInt())%(LakeConfiguration.width-20) + 10, Math.abs(LakeConfiguration.random.nextInt())%(LakeConfiguration.height-20) + 10);
		}
		return newObjectPosition;
	}
	
	public void setFeedProbability(int probability){ //probability is between 1-100
	  feedProbability = 100 - probability;
	}

	public boolean isWorldRecorded() {
		return recorded;
	}

	public void stopRecording() {
		recorded = false;
	}

	public void startRecording(String filename) {
		synchronized (this) {
			recordDirectoryName = filename;
			recorded = true;
		}
	}

	public String getRecordDirectoryName() {
		return recordDirectoryName;
	}

	public RenderedImage getWorldImage() {
		return graphicSystem.getImage();
	}

	public void removeLakeObject(LakeObject lakeObject) {
		synchronized (this) {
			lakeObjects.remove(lakeObject);
			destroyBody(lakeObject.body);
			if(chosen == lakeObject){
				chosen = null;
				setChosenLakeObject(null);
			}
		}
	}
}
