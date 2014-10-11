package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

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
	protected float radius;
	private Vec2 position;
	private Image image;
	private Color color;
	
	public abstract String getType();
	
	public LakeObject(LakeWorld world, float radius, Vec2 position, Color color){
		this.lakeWorld = world;
		this.position = position;
		this.radius = radius;
		this.color = color;
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
	
	protected BufferedImage createImage(Color color, BufferedImage mask){
	  int width = mask.getWidth();
	  int height = mask.getHeight();
	 
	  int[] maskPixels = mask.getRGB(0, 0, width, height, null, 0, width);

	  for (int i = 0; i < maskPixels.length; i++){
	    int alpha = maskPixels[i] << 24; // Shift green to alpha
	    maskPixels[i] = color.getRGB() | alpha;
	    System.out.println(alpha + " " + maskPixels[i])
	  }

	  mask.setRGB(0, 0, width, height, maskPixels, 0, width);
	  
	  return mask;
	}
}
