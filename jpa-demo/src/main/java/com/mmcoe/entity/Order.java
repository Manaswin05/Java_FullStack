package com.mmcoe.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "orders")
public class Order {
	@Id @GeneratedValue @Column(name = "ord_no")
	private int orderNo;
	@Column(name = "ord_date")
	private LocalDate orderDate;
	@Column(name = "ord_amt")
	private double amount;
	
	@ManyToOne @JoinColumn(name = "cid")
	private Customer customer;

	public Order() {
	}

	public Order(LocalDate orderDate, double amount) {
		this.orderDate = orderDate;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderDate=" + orderDate + ", amount=" + amount + "]";
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public double getAmount() {
		return amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
