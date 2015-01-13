package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.swing.JPanel;

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.omg.CORBA.BooleanHolder;

import com.edu.agh.student.lakeproject.fish.EyeView;
import com.edu.agh.student.lakeproject.fish.impl.ArrayEyeView;
import com.edu.agh.student.lakeproject.obstacle.Obstacle;
import com.edu.agh.student.lakeproject.userinterface.LakeObjectFocusListener;
import com.edu.agh.student.lakeproject.userinterface.MainFrame;

public class GraphicSystem implements LakeObjectFocusListener{

	private class Vec2Holder {

		protected Vec2 value;

	}

	private class BodyHolder {

		public Body value;

	}

	private LakeWorld lakeWorld;
	private Image buffer;
	private JPanel container;
	private LakeObject chosenObject;
	private RenderedImage imageToSave;

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
		this.imageToSave = (RenderedImage) this.buffer;
		buffer.getGraphics().dispose();
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
	    g.dispose();
	 
	    return result;
	}
	
	//dostarczaj informacji o rzeczach wokolo
	
	public EyeView getEyeView(LakeObject fish, float radius, int viewSize){
		ArrayEyeView result = new ArrayEyeView(viewSize);
		float angleZero = - (float) ((fish.getAngle() + 180) / 180 * Math.PI);
		float angleDelta = (float) (2 * Math.PI / viewSize);
		float angle = angleZero;
		Color pixel;
		Vec2 pointA = fish.getPosition();
		Vec2 pointB;
		LakeObject visibleObject = null;
		final BodyHolder visibleBody = new BodyHolder();
		final Vec2Holder visiblePoint = new Vec2Holder();
		float distance = 0;
		for(int ii = 0; ii < viewSize; ii++){
			visibleBody.value = null;
			visiblePoint.value = null;
			visibleObject = null;
			
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
			if(visibleBody.value != null){
				if(visibleBody.value.m_userData instanceof LakeObject)
					visibleObject = (LakeObject)visibleBody.value.m_userData;
				distance = visiblePoint.value.sub(pointA).length();
				if(distance > radius){
					visibleObject = null;
					visibleBody.value = null;
				}
			}
			if(chosenObject == fish && chosenObject.isActive()){
				this.lakeWorld.getGraphicSystem().buffer.getGraphics().setColor(Color.WHITE);
				this.lakeWorld.getGraphicSystem().buffer.getGraphics().drawLine((int)pointA.x, (int)pointA.y, (int)pointB.x, (int)pointB.y);
			}
			pixel = Color.black;
			if(visibleObject != null)
				pixel = visibleObject.getColor();
			else if(visibleBody.value != null)
				pixel = Color.GRAY;
			result.appendPixel(pixel);
			angle += angleDelta;
		}
		if(chosenObject == fish && chosenObject.isActive()){
			int x = 10 * (int)(viewSize/2);
			for(Object color: result.getPixels()){
				Graphics gd = MainFrame.getCanvas().getGraphics();
				gd.setColor((Color) color);
				gd.fillRect(x, 0, 10, 10);
				x-=10;
				if(x < 0)
					x = 90;
			}
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

	@Override
	public void setChosenLakeObject(LakeObject chosen) {
		chosenObject = chosen;
	}

	public RenderedImage getImage() {
		return imageToSave;
	}
}
