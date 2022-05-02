package com.loyalty.repository;

import org.springframework.data.repository.CrudRepository;

import com.loyalty.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
}
