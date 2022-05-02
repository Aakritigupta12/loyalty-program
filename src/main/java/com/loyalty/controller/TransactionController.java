package com.loyalty.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loyalty.model.Customer;
import com.loyalty.model.Transaction;
import com.loyalty.service.CustomerService;
import com.loyalty.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	CustomerService customerService;

	@GetMapping("/transaction")
	public List<Transaction> getAllTransaction() {
		return transactionService.getAllTransaction();
	}
	
	@PostMapping("/transaction")
	public void makeTransaction(@RequestParam String customerId, 
								@RequestParam String transactionValue, @RequestParam String transactionDate ) {
		
		Customer customer = customerService.getCustomerByCustomerId(Long.parseLong(customerId));
		if(customer!=null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale( Locale.UK );  
			Transaction transaction = new Transaction(customer, Float.parseFloat(transactionValue), LocalDate.parse(transactionDate, formatter));
			transactionService.saveOrUpdate(transaction, customer);
		}
		
	}

//	@GetMapping("/transaction/showForm")
//	public List<Transaction> showForm() {
//		return transactionService.getAllTransaction();
//	}
//	
//	@GetMapping("/transaction/{id}")
//	public Transaction getTransaction(@PathVariable("id") Long id) {
//		return transactionService.getTransactionByTransactionId(id);
//	}
//
//	@DeleteMapping("/transaction/{id}")
//	public void deleteTransaction(@PathVariable("id") Long id) {
//		transactionService.delete(id);
//	}


}
