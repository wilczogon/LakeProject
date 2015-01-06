package com.edu.agh.student.lakeproject.lakeworld;
import java.util.TimerTask;


public class LakeTimerTask extends TimerTask {
	private LakeWorld world;
	
	
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
