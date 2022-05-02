package com.loyalty.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loyalty.model.Customer;
import com.loyalty.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getAllCustomer() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));		
		return customers;
	}

	public Customer getCustomerByCustomerId(Long id) {
		return customerRepository.findById(id).get();
	}
	

	public void saveOrUpdate(Customer customer) {
		customerRepository.save(customer);
	}
	
	public List<Customer> saveAll(List<Customer> customers) {
		customerRepository.saveAll(customers);
		return customers;
	}

//	public void delete(Long customerId) {
//		customerRepository.deleteById(customerId);
//	}
}