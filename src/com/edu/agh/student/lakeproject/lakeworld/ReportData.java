package com.edu.agh.student.lakeproject.lakeworld;

import java.util.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
  
  public void generateReport(String directoryName) throws IOException{
    FileWriter fw;
    BufferedWriter bw;
    
    File fileTheLongestLivingFish = new File(directoryName + File.separator + "theLongestLivingFish");
    fileTheLongestLivingFish.mkdirs();
    
    if(!fileTheLongestLivingFish.exists())
      fileTheLongestLivingFish.createNewFile();
    
    fw = new FileWriter(fileTheLongestLivingFish.getAbsoluteFile());
    bw = new BufferedWriter(fw);
    
    for(Map.Entry<Integer, Integer> data: theLongestLivingFish.entrySet()){
      bw.write(data.getKey() + ", " + data.getValue());
      bw.newLine();
    }
    
    bw.close();
    
    
    
    File fileNumberOfFishes = new File(directoryName + File.separator + "numberOfFishes");
    fileNumberOfFishes.mkdirs();
    
    if(!fileNumberOfFishes.exists())
      fileNumberOfFishes.createNewFile();
    
    fw = new FileWriter(fileNumberOfFishes.getAbsoluteFile());
    bw = new BufferedWriter(fw);
    
    for(Map.Entry<Integer, Integer> data: numberOfFishes.entrySet()){
      bw.write(data.getKey() + ", " + data.getValue());
      bw.newLine();
    }
    
    bw.close();
    
    
    File fileMeanOfFishesLifeTime = new File(directoryName + File.separator + "meanOfFishesLifeTime");
    fileMeanOfFishesLifeTime.mkdirs();
    
    if(!fileMeanOfFishesLifeTime.exists())
      fileMeanOfFishesLifeTime.createNewFile();
      
    fw = new FileWriter(fileMeanOfFishesLifeTime.getAbsoluteFile());
    bw = new BufferedWriter(fw);
    
    for(Map.Entry<Integer, Float> data: meanOfFishesLifeTime.entrySet()){
      bw.write(data.getKey() + ", " + data.getValue());
      bw.newLine();
    }
    
    bw.close();
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
