package com.edu.agh.student.lakeproject.lakeworld;

import java.util.ArrayList;
import java.util.List;

public class ReportManager{
  private LakeWorld lakeWorld;
  private boolean isStopped = true;
  private List<ReportData> report;

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
  }
  
  public void generateReport(String directoryName){ //TODO
  }
  
  public void step(){
    for(ReportData reportData: report)
      reportData.step();
      
    if(!isStopped())
      count();
  }
  
  public boolean isStopped(){
    return isStopped;
  }
  
  private void count(){ //TODO
    List<LakeObject> lakeObjects = lakeWorld.getLakeObjects();
    
    for(LakeObject lakeObject: lakeObjects){
      if(lakeObject instanceOf Fish){
	Fish fish = (Fish)lakeObject;
	String spaceName = fish.getSpace();
	
	for(ReportData reportData: report){
	  if(spaceName.equals(reportData.getSpaceName())){
	    reportData.reportFishLifeTime(fish.getAge());
	  }
	}
      }
    }
    
    for(ReportData reportData: report)
      reportData.writeDown();
    
  }
  
} 
