package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Color;
import java.awt.Image;
import java.io.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.Fixture;

public abstract class LakeObject{
	
	protected Body body;
	protected Fixture fixture;
	protected LakeWorld lakeWorld;
	public LakeWorld getLakeWorld() {
		return lakeWorld;
	}

	protected float radius;
	protected Vec2 position;
	protected Image image;
	protected Color color;
	
	public abstract String getType();
	
	public LakeObject(LakeWorld world, float radius, Vec2 position, Color color){
		this.lakeWorld = world;
		this.position = position;
		this.radius = radius;
		this.color = color;
	}
	
	public LakeObject(LakeWorld lakeWorld, ObjectInputStream in) throws IOException, ClassNotFoundException{
		this.lakeWorld = lakeWorld;
		position = (Vec2)in.readObject();
		radius = in.readFloat();
		color = (Color)in.readObject();
	}
	
	public void setBody(Body body){
		this.body = body;
		FixtureDef fixDef = new FixtureDef();
		CircleShape circle = new CircleShape();
		circle.m_radius = radius;
		fixDef.shape = circle;
		fixture = body.createFixture(fixDef);
	}

	public void interactWith(LakeObject lakeObject){
		return;
	}
	
	public abstract void move();
	
	public void setRadius(float radius){
		if(this.radius != radius){
		  this.radius = radius;
		  ((CircleShape)fixture.m_shape).m_radius = radius;
		}
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void setInitialPosition(Vec2 position){
		this.position = position;
	}
	
	public Vec2 getPosition(){
		return body.getPosition();
	}
	
	public float getAngle(){
		return body.getAngle();
	}
	
	protected void setImage(String fileName){
		File img = new File(fileName);
		try {
			image = createImage(color, ImageIO.read(img));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public Image getImage(){
		return image;
	}
	
	public Vec2 getInitialPosition(){
		return position;
	}
	
	public boolean isActive(){
		return true;
	}

	public Color getColor(){
	  return color;
	}
	
	private BufferedImage createImage(Color color, BufferedImage mask){
	  for (int y = 0; y < mask.getHeight(); y++) {
	    for (int x = 0; x < mask.getWidth(); x++) {
	      Color maskC = new Color(mask.getRGB(x, y));
	      Color maskedColor = new Color(color.getRed(), color.getGreen(), color.getBlue(),
    		maskC.getRed());
	      mask.setRGB(x, y, maskedColor.getRGB());
	    }
	  }
	  return mask;
	}
	
	public void writeToStream(ObjectOutputStream out) throws IOException{
	  out.writeObject(this.getClass().getName());
	  out.writeObject(position);
	  out.writeFloat(radius);
	  out.writeObject(color);
	}
}
