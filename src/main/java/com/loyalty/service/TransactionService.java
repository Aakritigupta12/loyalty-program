package com.loyalty.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyalty.model.Customer;
import com.loyalty.model.Transaction;
import com.loyalty.repository.TransactionRepository;
import java.time.temporal.ChronoUnit;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomerService customerService;

	public List<Transaction> getAllTransaction() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactionRepository.findAll().forEach(transaction -> transactions.add(transaction));
		return transactions;
	}

//	public Transaction getTransactionByTransactionId(Long transactionId) {
//		return transactionRepository.findById(transactionId).get();
//	}

	public void saveOrUpdate(Transaction transaction, Customer  customer) {
		
		Float newPoints = 0.0F;

		if (transaction.getTransactionValue() > 0 && transaction.getTransactionValue() <= 5000) {
			newPoints = transaction.getTransactionValue();
		} else if (transaction.getTransactionValue() > 5000 && transaction.getTransactionValue() <= 7500) {
			newPoints = 5000 + ((7500 - transaction.getTransactionValue()) * 2);
		} else if (transaction.getTransactionValue() > 7500) {
			Float eligiblePoints = transaction.getTransactionValue()-7500;
			newPoints = 5000 + (2500 * 2) + (eligiblePoints*3);
		}
		List<Transaction> transactions = customer.getTransactions();
		
		if(!transactions.isEmpty()) {
			
			Collections.sort(transactions, Collections.reverseOrder());
			LocalDate latestTransactionDate = transactions.get(0).getTransactionDate();
			LocalDate sevenDaysBackDate = latestTransactionDate.minusDays(7);
			LocalDate fiveWeekBackDate = latestTransactionDate.minusWeeks(5);
			LocalDate todayDate = transaction.getTransactionDate();
			
			if(latestTransactionDate.isBefore(fiveWeekBackDate)) {
				customer.setCustomerPoints(0.0F);
				customer.setTempPointsForWeek(0.0F);
			}
			
			//check if transaction was done in last week
			if(latestTransactionDate.isAfter(sevenDaysBackDate)) {
				long diff = ChronoUnit.DAYS.between( latestTransactionDate, todayDate );
				//to check what was the difference between last and current transaction date
				
				if( diff <= 1 && diff>0 ) {
					customer.setTempPointsForWeek(customer.getTempPointsForWeek()+newPoints);
					
					//get transactions of this week having date as one week before current transaction date
					List<Transaction> validWeekTransactions = transactions.stream().filter(t1 -> t1.getTransactionDate().equals(todayDate.minusDays(7))).collect(Collectors.toList());
					
					//get transactions of this week having transaction value of >=500
					List<Transaction> validAmountTransactions = transactions.stream().filter(t2 -> t2.getTransactionValue()>=500 && t2.getTransactionDate().isAfter(sevenDaysBackDate) && t2.getTransactionDate().isBefore(todayDate)).collect(Collectors.toList());
					
					if(!validWeekTransactions.isEmpty() && !validAmountTransactions.isEmpty()) {
						customer.setCustomerPoints(customer.getCustomerPoints()+customer.getTempPointsForWeek());
						customer.setTempPointsForWeek(0.0F);
					}
				}
				else {
					//set current transaction points as new points
					customer.setTempPointsForWeek(newPoints);
				}
			}
			
			
		}			
		else {
			//if no transaction put current transaction points 
			customer.setTempPointsForWeek(newPoints);
		}
		
		customerService.saveOrUpdate(customer);
		transactionRepository.save(transaction);
	}

//	public void delete(Long transactionId) {
//		transactionRepository.deleteById(transactionId);
//	}
}