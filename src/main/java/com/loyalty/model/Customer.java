package com.loyalty.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	private String customerName;
	
	private float customerPoints;
	
	private float tempPointsForWeek;
	
	@OneToMany(mappedBy="customer",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();
	
	public Customer() {}

	public Customer(String customerName) {
		super();
		this.customerName = customerName;
		this.customerPoints = 0;
		this.tempPointsForWeek = 0;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public float getCustomerPoints() {
		return customerPoints;
	}

	public void setCustomerPoints(float customerPoints) {
		this.customerPoints = customerPoints;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

	public float getTempPointsForWeek() {
		return tempPointsForWeek;
	}

	public void setTempPointsForWeek(float tempPoints) {
		this.tempPointsForWeek = tempPoints;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPoints="
				+ customerPoints + ", transactions=" + transactions + "]";
	}

	
}
