package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Transaction;
import java.sql.Timestamp; 

@Repository
public class TransactionRepository {
	
	static private int counter=0;
	
	private static List<Transaction> transactioncollection = new ArrayList<Transaction>();

	public List<Transaction> getSortedTransactionCollection() {
		
		Collections.sort(transactioncollection,new Comparator<Transaction>(){
	           
			  @Override
			    public int compare(Transaction a, Transaction b){
			        
			        
			        return a.getTimestamp().compareTo(b.getTimestamp()); //decreasing order
			    }
			});
		return transactioncollection;
	}

	public Transaction addTransactioncollection(Transaction t) {
		
		transactioncollection.add(t);
		return t;		
	
	}

	public void removeTransaction(int id) {
		
		for(int j=0;j<transactioncollection.size();j++) {
			
			Transaction t = transactioncollection.get(j);
			if(t.getId()==id) {
				transactioncollection.remove(j);
				break;
			}
			
		}
		
	}

	public void updateTransactionsPoints(int updatePoints, Long id) {
		
		for(Transaction t : transactioncollection) {
			if(t.getId()==id)
				t.setPoints(t.getPoints()+updatePoints);	
		}
		
	}
	
	

	

}
