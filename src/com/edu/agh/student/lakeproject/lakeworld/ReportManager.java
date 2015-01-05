package com.edu.agh.student.lakeproject.lakeworld;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import com.edu.agh.student.lakeproject.fish.Fish;

public class ReportManager{
  private LakeWorld lakeWorld;
  private boolean isStopped = true;
  private List<ReportData> report;
  private int time = 0;

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
    time = 0;
  }
  
  public void generateReport(String directoryName) throws IOException{
  
    for(ReportData reportData: report){
      reportData.generateReport(directoryName +  File.separator + reportData.getSpaciesName().replace(".", "_"));
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
  
} 
