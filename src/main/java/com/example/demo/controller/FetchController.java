package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Payer;
import com.example.demo.entity.Points;
import com.example.demo.entity.Transaction;
import com.example.demo.service.FetchService;

import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class FetchController {
	
	@Autowired
	FetchService fetchService;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FetchController.class);
	
	//add transaction
	  
	@PostMapping(path = "/payer/transaction", consumes = "application/json", produces = "application/json")
	  ResponseEntity<Transaction> addPoints(@RequestBody Transaction transaction) {
		
		try {
				
			Transaction savedTransaction = fetchService.addPoints(transaction);
		
			
			return ResponseEntity.ok(savedTransaction);
			
		} catch (Exception ex) {
			return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	 
	  //spend points
	  
	  @PostMapping(path = "/payer/spend", consumes = "application/json", produces = "application/json")
	  ResponseEntity<List<JSONObject>> spendPoints(@RequestBody Points points) {
		
    	
		try {
				
			Map<String,Integer> usedPoints = fetchService.spendPoints(points);
			
			Iterator<Entry<String, Integer>> hmIterator = usedPoints.entrySet().iterator();
			
			List<JSONObject> entities = new ArrayList<JSONObject>();

			
			while(hmIterator.hasNext()) {
				
			     Map.Entry mapElement
	                = (Map.Entry)hmIterator.next();
			
			        JSONObject entity = new JSONObject();
			        entity.put("payer", mapElement.getKey());
			        entity.put("points", mapElement.getValue());
			        entities.add(entity);


			}
		    return new ResponseEntity<List<JSONObject>>(entities, HttpStatus.OK); // XXX

			
			
		} catch (Exception ex) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	  
	  @GetMapping(path = "/payer/balance", consumes = "application/json", produces = "application/json")
	  ResponseEntity<List<JSONObject>> getPoints() {
		  
	  try {
		  List<Payer> payerList = fetchService.getAllPayerBalances().getPayercollection();
		  
		  List<JSONObject> entities = new ArrayList<JSONObject>();
		  
		  for(Payer p : payerList) {
			
		        JSONObject entity = new JSONObject();
		        entity.put("payer", p.getPayer());
		        entity.put("points", p.getPoints());
		        entities.add(entity);
		  }
		  
		  return new ResponseEntity<List<JSONObject>>(entities, HttpStatus.OK);
		}
		catch (Exception ex) {
		   return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	  }
	  
	  

}
