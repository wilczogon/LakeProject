package com.edu.agh.student.lakeproject.lakeworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.io.*;
import com.edu.agh.student.lakeproject.fish.Fish;

public class ReportManager{
  private LakeWorld lakeWorld;
  private boolean isStopped = true;
  private List<ReportData> report;
  private int time = 0;
  private Map<Integer, Fish> bestFishes = new HashMap<Integer, Fish>();

  public ReportManager(LakeWorld lakeWorld){
    this.lakeWorld = lakeWorld;
    report = new ArrayList<ReportData>();
  }

  public void start(){
    isStopped = false;
  }
  
  public void stop(){
    isStopped = true;
  }
  
  public void reset(){
    report = new ArrayList<ReportData>();
    bestFishes = new HashMap<Integer, Fish>();
    time = 0;
  }
  
  public void generateReport(String directoryName) throws IOException{
  
    for(ReportData reportData: report){
      reportData.generateReport(directoryName +  File.separator + reportData.getSpaciesName().replace(".", "_"));
    }
    
    //TODO save bestFishes
    int i = 0;
    for(Fish fish: bestFishes.values()){
      File file = new File(directoryName + File.separator + "best_fishes" + File.separator + i);
      
      if(!file.exists()){
	file.getParentFile().mkdirs();
	file.createNewFile();
      }
    
      OutputStream out = new BufferedOutputStream(new FileOutputStream(directoryName + File.separator + "best_fishes" + File.separator + i));
      ObjectOutputStream oout = new ObjectOutputStream(out);
      fish.writeToStream(oout);
      //oout.flush();
      oout.close();
      ++i;
    }
  }
  
  public void step(){
    if(!isStopped()){
      ++time;
      for(ReportData reportData: report)
	reportData.step();
      count();
    }
  }
  
  public boolean isStopped(){
    return isStopped;
  }
  
  private void count(){
    List<LakeObject> lakeObjects = lakeWorld.getLakeObjects();
    
    for(LakeObject lakeObject: lakeObjects){
      if(lakeObject instanceof Fish){
	Fish fish = (Fish)lakeObject;
	String spaceName = fish.getSpecies();
	boolean reportDataFound = false;
	
	for(ReportData reportData: report){
	  if(spaceName.equals(reportData.getSpaciesName())){
	    reportData.reportFishLifeTime(fish.getAge());
	    reportDataFound = true;
	    break;
	  }
	}
	
	if(!reportDataFound){
	  ReportData reportData = new ReportData(fish.getSpecies(), time);
	  reportData.reportFishLifeTime(fish.getAge());
	  report.add(reportData);
	}
      }
    }
    
    for(ReportData reportData: report)
      reportData.writeDown();
    
  }
  
  public void reportDeadFish(Fish fish){
    if(bestFishes.size() < 10){
      fish.reset();
      bestFishes.put(fish.getAge(), fish);
    }else{
      int min = Collections.min(bestFishes.keySet());
      if(min < fish.getAge()){
	fish.reset();
	bestFishes.put(fish.getAge(), fish);
	bestFishes.remove(min);
      }
    }
  }
  
} 
