package com.edu.agh.student.lakeproject.lakeworld;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class GraphicSystem {

	private LakeWorld lakeWorld;
	private Image buffer;
	private JFrame container;

	public GraphicSystem(LakeWorld lakeWorld, final JFrame app){
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
}
