package com.edu.agh.student.lakeproject.lakeworld;

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
    if(!isStopped())
      count();
  }
  
  public boolean isStopped(){
    return isStopped;
  }
  
  private void count(){ //TODO
  }
  
} 
