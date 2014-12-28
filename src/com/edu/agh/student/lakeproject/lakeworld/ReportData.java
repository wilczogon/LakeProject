package com.edu.agh.student.lakeproject.lakeworld;

import java.util.*;

public class ReportData{
  private String spaceName;
  private Map<Integer, Integer> theLongestLivingFish = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> numberOfFishes = new HashMap<Integer, Integer>();
  private Map<Integer, Float> meanOfFishesLifeTime = new HashMap<Integer, Float>();
  private int fishNo = 0;
  private int fishLifeTimeSum = 0;
  private int lifeTimeOfTheLongestLivingFish = 0;
  private int time = 0;
  
  public ReportData(String spaceName){
    this.spaceName = spaceName;
  }
  
  public String getSpaceName(){
    return spaceName;
  }
  
  public void writeDown(){
    theLongestLivingFish.put(time, lifeTimeOfTheLongestLivingFish);
    numberOfFishes.put(time, fishNo);
    meanOfFishesLifeTime.put(time, (float)fishLifeTimeSum/(float)fishNo);
  }
  
  public void step(){
    time++;
    fishNo = 0;
    fishLifeTimeSum = 0;
    lifeTimeOfTheLongestLivingFish = 0;
  }
  
  public void reportFishLifeTime(int lifeTime){
    fishNo++;
    fishLifeTimeSum += lifeTime;
  }
  
}
