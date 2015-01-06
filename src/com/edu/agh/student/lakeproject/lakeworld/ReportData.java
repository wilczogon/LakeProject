package com.edu.agh.student.lakeproject.lakeworld;

import java.util.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReportData{
  private String spaciesName;
  private Map<Integer, Integer> theLongestLivingFish = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> numberOfFishes = new HashMap<Integer, Integer>();
  private Map<Integer, Float> meanOfFishesLifeTime = new HashMap<Integer, Float>();
  private int fishNo = 0;
  private int fishLifeTimeSum = 0;
  private int lifeTimeOfTheLongestLivingFish = 0;
  private int time;
  
  public ReportData(String spaciesName){
    this(spaciesName, 0);
  }
  
  public ReportData(String spaciesName, int time){
    this.spaciesName = spaciesName;
    this.time = time;
  }
  
  public void generateReport(String fileName) throws IOException{
    FileWriter fw;
    BufferedWriter bw;
    
    File file = new File(fileName);
    
    if(!file.exists()){
      file.getParentFile().mkdirs();
      file.createNewFile();
    }
    
    fw = new FileWriter(file.getAbsoluteFile());
    bw = new BufferedWriter(fw);
    
    bw.write("time, numberOfFishes, meanOfFishesLifeTime, theLongestLivingFish");
    bw.newLine();
    
    for(int t=0; t<time; ++t){
      bw.write(t + ", " + numberOfFishes.get(t) + ", " + meanOfFishesLifeTime.get(t) + ", " + theLongestLivingFish.get(t));
      bw.newLine();
    }
    
    bw.close();
  }
  
  public String getSpaciesName(){
    return spaciesName;
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
    
    if(lifeTime > lifeTimeOfTheLongestLivingFish)
      lifeTimeOfTheLongestLivingFish = lifeTime;
  }
  
}
