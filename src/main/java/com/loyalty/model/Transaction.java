package com.loyalty.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Transaction")
public class Transaction implements Comparable<Transaction> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private float transactionValue;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate transactionDate;

	public Transaction(Customer customer, float transactionValue, LocalDate transactionDate) {
		super();
		this.customer = customer;
		this.transactionValue = transactionValue;
		//this.transactionDate = LocalDate.now();
		this.transactionDate = transactionDate;
	}

	public Transaction() {
		
	}
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionValue(float transactionValue) {
		this.transactionValue = transactionValue;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public int compareTo(Transaction o) {
		return getTransactionDate().compareTo(o.getTransactionDate());
	}
	
	

	
}