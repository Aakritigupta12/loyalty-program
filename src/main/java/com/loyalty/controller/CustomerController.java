package com.loyalty.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loyalty.model.Customer;
import com.loyalty.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		
		if(customerService.getAllCustomer().isEmpty()) {
    		Customer c1 = new Customer("Rick Sanchez");
        	Customer c2 = new Customer("Morty Smith");
        	Customer c3 = new Customer("Summer Smith");
        	Customer c4 = new Customer("Jerry Smith");
        	Customer c5 = new Customer("Beth Smith");

        	List<Customer> list = new ArrayList<>();
        	list.add(c1);
        	list.add(c2);
        	list.add(c3);
        	list.add(c4);
        	list.add(c5);
        	
        	List<Customer> customers = customerService.saveAll(list);
        	
        	System.out.println(customers);
    	}
	}

	@GetMapping("/customer")
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}

//	@GetMapping("/customer/{id}")
//	public Customer getCustomer(@PathVariable("id") Long id) {
//		return customerService.getCustomerByCustomerId(id);
//	}
//
//	@DeleteMapping("/customer/{id}")
//	public void deleteCustomer(@PathVariable("id") Long id) {
//		customerService.delete(id);
//	}
//
//	@PostMapping("/customer")
//	public Long saveCustomer(@RequestBody Customer customer) {
//		customerService.saveOrUpdate(customer);
//		return customer.getCustomerId();
//	}
}
