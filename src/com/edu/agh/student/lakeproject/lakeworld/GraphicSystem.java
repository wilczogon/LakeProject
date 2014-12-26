package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.omg.CORBA.BooleanHolder;

import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.impl.ArrayEyeView;

public class GraphicSystem {

	private class Vec2Holder {

		protected Vec2 value;

	}

	private class BodyHolder {

		public Body value;

	}

	private LakeWorld lakeWorld;
	private Image buffer;
	private JPanel container;

	public GraphicSystem(LakeWorld lakeWorld, final JPanel app){
		this.lakeWorld = lakeWorld;
		this.container = app;
		buffer = new BufferedImage(this.container.getWidth(),this.container.getHeight(), BufferedImage.TYPE_INT_RGB);
	}
	
	public void paint(){
		for(LakeObject lakeObject: lakeWorld.getLakeObjects()){
			buffer.getGraphics().drawImage(rotateImage((BufferedImage)lakeObject.getImage(), lakeObject.getAngle()), 
					(int)(lakeObject.getPosition().x - lakeObject.getRadius()), 
					(int)(lakeObject.getPosition().y - lakeObject.getRadius()), 
					(int)(lakeObject.getRadius()*2), 
					(int)(lakeObject.getRadius()*2), 
					null);
		}
	}
	
	public void swapBuffers(){
		this.container.getGraphics().drawImage(buffer, 0, 0, container);
		this.buffer = new BufferedImage(this.container.getWidth(),this.container.getHeight(), BufferedImage.TYPE_INT_RGB);
	}
	
	public void save(String fileName){
		
	}
	
	//autor: http://udojava.com/tag/awt-image-rotation/
	private static BufferedImage rotateImage(BufferedImage src, double degrees) {
	    double radians = Math.toRadians(degrees);
	 
	    int srcWidth = src.getWidth();
	    int srcHeight = src.getHeight();
	 
	    BufferedImage result = new BufferedImage(srcWidth, srcHeight,
	            src.getType());
	    Graphics2D g = result.createGraphics();
	    g.rotate(radians, srcWidth / 2, srcHeight / 2);
	    g.drawRenderedImage(src, null);
	 
	    return result;
	}
	
	//dostarczaj informacji o rzeczach wokolo
	
	public EyeView getEyeView(LakeObject fish, float radius, int viewSize){
		ArrayEyeView result = new ArrayEyeView(viewSize);
		//System.out.println(fish.getAngle());
		float angleZero = (float) (fish.getAngle() * 180 / Math.PI);
		float angleDelta = (float) (2 * Math.PI / viewSize);
		float angle = angleZero;
		Color pixel;
		Vec2 pointA = fish.getPosition();
		Vec2 pointB;
		LakeObject visibleObject = null;
		final BodyHolder visibleBody = new BodyHolder();
//		final BooleanHolder stoppedHolder = new BooleanHolder(false);
		final Vec2Holder visiblePoint = new Vec2Holder();
		float distance = 0;
		for(int ii = 0; ii < viewSize; ii++){
			visibleBody.value = null;
			visiblePoint.value = null;
			angle += angleDelta;
			//pointB = new Vec2(pointA);
			pointB = pointA.add(new Vec2((float) Math.sin(angle)*radius,(float) Math.cos(angle)*radius));
			RayCastCallback callback = new RayCastCallback() {
				
				@Override
				public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal,
						float fraction) {
					visibleBody.value = fixture.getBody();
					visiblePoint.value = point;
					return 0;
				}
			};
			lakeWorld.raycast(callback, pointA, pointB);
			for(LakeObject lakeObject: lakeWorld.getLakeObjects()){
				if(lakeObject.body == visibleBody.value){
					visibleObject = lakeObject;
					break;
				}
			}
			if(visibleObject != null){
				distance = visibleObject.body.getPosition().sub(pointA).length();
				if(distance > radius){
					visibleObject = null;
				}
			}
			pixel = Color.black;
			if(visibleObject != null)
				pixel = visibleObject.getColor();
			result.appendPixel(pixel);
		}
		return result;
	}
	public static Color getFadedColor(Color color, float distance){
		Color result;
		float fadingFactor = 1/(distance+1);
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		red *= fadingFactor;
		green *= fadingFactor;
		blue *= fadingFactor;
		result = new Color(red, green, blue);
		return result;
	}
}
