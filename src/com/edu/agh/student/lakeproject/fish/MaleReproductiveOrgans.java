package com.edu.agh.student.lakeproject.fish;

public abstract class MaleReproductiveOrgans extends ReproductiveOrgans{
  public MaleReproductiveOrgans(){
    gender = Gender.MALE;
  }
  
  public abstract Chromosome getChromosome();
}