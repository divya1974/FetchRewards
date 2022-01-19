package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"id"})
public class Payer {

  private String payer;
  private int points;

  public Payer(String payer, int points) {
    this.payer = payer;
    this.points = points;
  }


public String getPayer() {
	return payer;
}

public void setPayer(String payer) {
	this.payer = payer;
}

public int getPoints() {
	return points;
}

public void setPoints(int points) {
	this.points = points;
}
  
public void addPoints(int point) {
	this.points = this.points+point;

}
}
