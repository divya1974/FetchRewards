package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Payer;
import com.example.demo.entity.Points;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.PayerRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.service.FetchService;

@SpringBootTest
class FetchRewardsApplicationTests {
	
	@Autowired
	private FetchService fetchRewardsService;
	  

	@Test
	void contextLoads() {
	}
	
	 @Test
	 public void addPoints() throws ParseException {
	    
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		  sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 
		 Transaction newtransaction1 = new Transaction("DANNON", 1000, sf.parse("2020-11-02T14:00:00Z"));
	
		 Transaction newtransaction2 = new Transaction("UNILEVER", 200, sf.parse("2020-10-31T11:00:00Z"));
		 Transaction newtransaction3 = new Transaction("DANNON", -200, sf.parse("2020-10-31T15:00:00Z"));
		 Transaction newtransaction4 = new Transaction("MILLER COORS", 10000, sf.parse("2020-11-01T13:00:00Z"));
		 Transaction newtransaction5 = new Transaction("DANNON", 300, sf.parse("2020-10-31T10:00:00Z"));
		 
		 Transaction savedtransaction1 = fetchRewardsService.addPoints(newtransaction1);
		 Transaction savedtransaction2 = fetchRewardsService.addPoints(newtransaction2);
		 Transaction savedtransaction3  = fetchRewardsService.addPoints(newtransaction3);
		 Transaction savedtransaction4  = fetchRewardsService.addPoints(newtransaction4);
		 Transaction savedtransaction5  = fetchRewardsService.addPoints(newtransaction5);
	    
	     assertEquals(savedtransaction1.getPayer(),newtransaction1.getPayer());
	     assertEquals(savedtransaction2.getPayer(),newtransaction2.getPayer());
	     assertEquals(savedtransaction3.getPayer(),newtransaction3.getPayer());
	     assertEquals(savedtransaction4.getPayer(),newtransaction4.getPayer());
	     assertEquals(savedtransaction5.getPayer(),newtransaction5.getPayer());

	 } 
	 
	 @Test
	 public void spendPoints() throws ParseException {
		 
			
		 Map<String,Integer> spentMap = fetchRewardsService.spendPoints(new Points(5000));
		 
				  
		  assertEquals(spentMap.get("UNILEVER"),-200);
		  assertEquals(spentMap.get("DANNON"),-100);
		  assertEquals(spentMap.get("MILLER COORS"),-4700);
		 
		 
	 } 
	 
	 @Test
	 public void getPlayerPoints() throws ParseException {
		 
		  PayerRepository payerBalanceList = fetchRewardsService.getAllPayerBalances();
		
		  assertEquals(payerBalanceList.getPayer("UNILEVER").getPoints(),0);
		  assertEquals(payerBalanceList.getPayer("MILLER COORS").getPoints(),5300);
		  assertEquals(payerBalanceList.getPayer("DANNON").getPoints(),1000);
	
	 }
	 
	 
	 }
	 
	 


