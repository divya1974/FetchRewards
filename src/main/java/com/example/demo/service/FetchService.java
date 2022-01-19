package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payer;
import com.example.demo.entity.Points;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.PayerRepository;
import com.example.demo.repository.TransactionRepository;


@Service
public class FetchService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	PayerRepository payerRepository;
	
	
	
	   public Map<String,Integer> spendPoints(Points points){
		   
		
		   Map<String,Integer> remainingMap = new HashMap<String, Integer>();
		   Map<String,Integer> addedMap = new HashMap<String, Integer>();
		   
		   for(Payer p : payerRepository.getPayercollection()) {
			   
			   remainingMap.put(p.getPayer(), p.getPoints());
			   addedMap.put(p.getPayer(), 0);
			   
		   }
		   
		   
		  int reqpoints = points.getPoints();
		   
		  List<Transaction> list= transactionRepository.getSortedTransactionCollection();
		  
		  List<Integer> removeTransaction = new ArrayList<Integer>();
		  
		
		  for(int j=0;j<list.size();j++)
			  	
		  {
			  Transaction t = list.get(j);
			  int spent = 0;
			  
			  Integer payerTotalPoints= remainingMap.get(t.getPayer());
			  
			  if(reqpoints==0)
				  break;
				  
			  if(payerTotalPoints<=0)
				  continue;
			 
			  else 
			 
			  {
				  
				  spent = Math.min(t.getPoints(), Math.min(payerTotalPoints, reqpoints));		  
				  payerTotalPoints-= spent;
				  reqpoints-=spent;
				  
					  
	     	  remainingMap.put(t.getPayer(),payerTotalPoints);
			  addedMap.put(t.getPayer(), addedMap.get(t.getPayer()) + spent);
			  
			  if(spent==t.getPoints())
				  removeTransaction.add(t.getId());
				
			  
			  }

			 
     	  }
		  	  
		 for(int r : removeTransaction)
		  transactionRepository.removeTransaction(r);
		  
		 
		  for(int i=0;i<payerRepository.getPayercollection().size();i++) {
			   
			  Payer p = payerRepository.getPayercollection().get(i);
			  p.setPoints(remainingMap.get(p.getPayer()));
			  addedMap.put(p.getPayer(), -1*addedMap.get(p.getPayer()));
		
		   }
		  	  
		   return addedMap;
		 
		  }

	  public Transaction addPoints(Transaction transaction) {
		  
		  Payer p;
		  
		  
		  if(payerRepository.getPayer(transaction.getPayer())!=null) {
			
			  p= payerRepository.getPayer(transaction.getPayer());
			  p.addPoints(transaction.getPoints());
		  }
		  
		  else
		  {
			  p = new Payer(transaction.getPayer(),transaction.getPoints());
			  payerRepository.addPayer(p); 
			  
		  }
		  
		
		 return transactionRepository.addTransactioncollection(transaction);	  
		  
	  }

	  public List<Transaction> getAllTransactions() {
		  
	       return transactionRepository.getSortedTransactionCollection();
		  
		  
	  }
	  

	  public PayerRepository getAllPayerBalances(){
		  
		return payerRepository;
		  
	  }
	  
	  

}
