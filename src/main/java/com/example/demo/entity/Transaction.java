package com.example.demo.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"id"})
public class Transaction {

  @Id
  @GeneratedValue
  int id;
  private String payer;
  private int points;
  private Date timestamp;
  private static int count=0;
  
  
public Transaction(String payer, int i, Date date) {
	
	this.id=count++;
	this.payer = payer;
	this.points = i;

    this.timestamp =  date;
    
	// TODO Auto-generated constructor stub
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public Date getTimestamp() {
	return timestamp;
}
public void setTimestamp(Date timestamp) {
	this.timestamp = timestamp;
}
}
