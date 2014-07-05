package lessur.engine.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.MathUtils;
import org.jbox2d.common.OBBViewportTransform;
import org.jbox2d.common.Transform;
import org.jbox2d.common.Vec2;
import org.jbox2d.pooling.arrays.IntArray;
import org.jbox2d.pooling.arrays.Vec2Array;

import com.edu.agh.student.lakeproject.lakeworld.LakeConfiguration;

//import com.agh.student.pacyno.lakeproject.laketest.LakeTestConfiguration;

/**
* Implementation of JBox2d's DebugDraw using Slick2d
* @version 2.0
* @author liamzebedee
*
* Created by Liam Edwards-Playne | http://cryptum.net
* Licensed under GPLv2 license
*
* Copyright (C) 2011 Liam Edwards-Playne
*/
/*
* Example Code
*
 
Slick2DJBox2DDebugDraw debugDraw = new Slick2DJBox2DDebugDraw(gameContainer);
debugDraw.setFlags(DebugDraw.e_shapeBit);
world.setDebugDraw(debugDraw); // Where world is your JBox2D world
 
*
* Available flags from JBox2D DebugDraw:
*
 
public static final int e_shapeBit = 0x0001; // < draw shapes
public static final int e_jointBit = 0x0002; // < draw joint connections
public static final int e_coreShapeBit = 0x0004; // < draw core (TOI) shapes
public static final int e_aabbBit = 0x0008; // < draw axis aligned bounding boxes
public static final int e_obbBit = 0x0010; // < draw oriented bounding boxes
public static final int e_pairBit = 0x0020; // < draw broad-phase pairs
public static final int e_centerOfMassBit = 0x0040; // < draw center of mass frame
public static final int e_controllerBit = 0x0080; // < draw controllers
 
*
* @see http://code.google.com/p/jbox2d/source/browse/branches/jbox2d-2.0.1/src/org/jbox2d/dynamics/DebugDraw.java
*/
public class Slick2dDebugDraw extends DebugDraw {
JFrame container;
public static int CIRCLE_POINTS = 20;
public Image img;
private final Vec2Array vec2Array = new Vec2Array();
private final Vec2 sp1 = new Vec2();
private final Vec2 sp2 = new Vec2();
private final Vec2 saxis = new Vec2();
private final Vec2 temp = new Vec2();
private final static IntArray xIntsPool = new IntArray();
private final static IntArray yIntsPool = new IntArray();
private final Vec2 temp2 = new Vec2();
 
public Slick2dDebugDraw(final JFrame app) {
super(new OBBViewportTransform());
this.viewportTransform.setYFlip(true);
this.viewportTransform.setCenter(LakeConfiguration.getLeftWallPosition(), LakeConfiguration.getTopWallPosition());
this.container = app;
this.img = new BufferedImage(this.container.getWidth(),this.container.getHeight(), BufferedImage.TYPE_INT_RGB);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawCircle1(org.jbox2d.common.Vec2, float, org.jbox2d.common.Color3f)
*/
@Override
public void drawCircle(final Vec2 center, final float radius, final Color3f color) {
final Vec2[] vecs = vec2Array.get(CIRCLE_POINTS );
generateCirle(center, radius, vecs, CIRCLE_POINTS);
drawPolygon(vecs, CIRCLE_POINTS, color);
}
/**
* @see org.jbox2d.callbacks.DebugDraw#drawPoint(org.jbox2d.common.Vec2, float, org.jbox2d.common.Color3f)
*/
@Override
public void drawPoint(final Vec2 argPoint, final float argRadiusOnScreen, final Color3f argColor) {
Graphics g = this.img.getGraphics();
getWorldToScreenToOut(argPoint, sp1);
g.setColor(new Color(argColor.x,argColor.y,argColor.z));
 
sp1.x -= argRadiusOnScreen;
sp1.y -= argRadiusOnScreen;
g.fillOval((int)sp1.x, (int)sp1.y, (int)argRadiusOnScreen*2, (int)argRadiusOnScreen*2);
g.setColor(Color.white);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawSegment(org.jbox2d.common.Vec2, org.jbox2d.common.Vec2, org.jbox2d.common.Color3f)
*/
@Override
public void drawSegment(final Vec2 p1, final Vec2 p2, final Color3f color) {
Graphics g = this.img.getGraphics();
getWorldToScreenToOut(p1, sp1);
getWorldToScreenToOut(p2, sp2);
g.setColor(new Color(color.x,color.y,color.z));
 
g.drawLine((int)sp1.x, (int)sp1.y, (int)sp2.x, (int)sp2.y);
g.setColor(Color.white);
}
 
public void drawAABB(final AABB argAABB, final Color3f color) {
Graphics g = this.img.getGraphics();
g.setColor(new Color(color.x, color.y, color.z));
final Vec2 vecs[] = vec2Array.get(4);
argAABB.getVertices(vecs);
drawPolygon(vecs, 4, color);
g.setColor(Color.white);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawSolidCircle(org.jbox2d.common.Vec2, float, org.jbox2d.common.Vec2, org.jbox2d.common.Color3f)
*/
@Override
public void drawSolidCircle(final Vec2 center, final float radius, final Vec2 axis, final Color3f color) {
Graphics g = this.img.getGraphics();
final Vec2[] vecs = vec2Array.get(CIRCLE_POINTS);
generateCirle(center, radius, vecs, CIRCLE_POINTS);
drawSolidPolygon(vecs, CIRCLE_POINTS, color);
if(axis != null) {
saxis.set(axis).mulLocal(radius).addLocal(center);
drawSegment(center, saxis, color);
}
g.setColor(Color.white);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawSolidPolygon(org.jbox2d.common.Vec2[], int, org.jbox2d.common.Color3f)
*/
@Override
public void drawSolidPolygon(final Vec2[] vertices, final int vertexCount, final Color3f color) {
Graphics g = this.img.getGraphics();
final int[] xInts = xIntsPool.get(vertexCount);
final int[] yInts = yIntsPool.get(vertexCount);
final Polygon p = new Polygon();
 
for(int i = 0; i < vertexCount; i++) {
getWorldToScreenToOut(vertices[i], temp);
xInts[i] = (int) temp.x;
yInts[i] = (int) temp.y;
p.addPoint(xInts[i], yInts[i]);
}
 
g.setColor(new Color(color.x,color.y,color.z));
g.fillPolygon(p); //Draws shape filled with colour
g.setColor(Color.white);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawString(float, float, java.lang.String, org.jbox2d.common.Color3f)
*/
@Override
public void drawString(final float x, final float y, final String s, final Color3f color) {
Graphics g = this.img.getGraphics();
g.setColor(new Color(color.x, color.y, color.z));
g.drawString(s, (int)x, (int)y);
g.setColor(Color.white);
}
 
/**
* @see org.jbox2d.callbacks.DebugDraw#drawTransform(org.jbox2d.common.Transform)
*/
@Override
public void drawTransform(final Transform xf) {
Graphics g = this.img.getGraphics();
getWorldToScreenToOut(xf.p, temp);
temp2.setZero();
final float k_axisScale = 0.4f;
 
g.setColor(new Color(1,0,0));
temp2.x = xf.p.x + k_axisScale * xf.q.c;
temp2.y = xf.p.y + k_axisScale * xf.q.s;
getWorldToScreenToOut(temp2, temp2);
g.drawLine((int)temp.x, (int)temp.y, (int)temp2.x, (int)temp2.y);
 
g.setColor(new Color(0,1,0));
temp2.x = xf.p.x + k_axisScale * xf.q.c;
temp2.y = xf.p.y + k_axisScale * xf.q.s;
getWorldToScreenToOut(temp2, temp2);
g.drawLine((int)temp.x, (int)temp.y, (int)temp2.x, (int)temp2.y);
g.setColor(Color.white);
}
 
// Circle Generator
private void generateCirle(final Vec2 argCenter, final float argRadius, final Vec2[] argPoints, final int argNumPoints) {
Graphics g = this.img.getGraphics();
final float inc = MathUtils.TWOPI / argNumPoints;
 
for(int i=0; i<argNumPoints; i++){
argPoints[i].x = (argCenter.x + MathUtils.cos(i*inc)*argRadius);
argPoints[i].y = (argCenter.y + MathUtils.sin(i*inc)*argRadius);
}
g.setColor(Color.white);
}


public void swapBuffers(){
	this.container.getGraphics().drawImage(img, 0, 0, container);
	this.img = new BufferedImage(this.container.getWidth(),this.container.getHeight(), BufferedImage.TYPE_INT_RGB);
}
}
