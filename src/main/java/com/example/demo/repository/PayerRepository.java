package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Payer;

@Repository
public class PayerRepository {

	
	static private List<Payer> payercollection = new ArrayList<Payer>();

	public List<Payer> getPayercollection() {
		return payercollection;
	}
	

	public void addPayer(Payer p) {
		
		payercollection.add(p);
		
	}
	
	public Payer getPayer(String payer) {
		
		for(Payer p : payercollection)
		{
			if(p.getPayer().equals(payer))
				return p;
		}
		return null;
		
	}
	
}
