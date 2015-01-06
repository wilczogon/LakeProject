package com.edu.agh.student.lakeproject.lakeworld;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;


public class LakeTimerTask extends TimerTask {
	private LakeWorld world;
	private int i = 0;
	
	public LakeTimerTask(LakeWorld world){
		this.world = world;
	}

	@Override
	public void run() {
		synchronized (world) {
			world.step();
		}
	}

}
