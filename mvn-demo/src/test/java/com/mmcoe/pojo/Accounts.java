package com.mmcoe.pojo;

public class Accounts {
	private double balance;
	
	public Accounts(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}
	public double deposit(double amount) {
		this.balance+=amount;
		return this.balance;
	}
	public double withdraw(double amount) throws BalanceException {
		if(balance <=0) {
			throw new BalanceException("Zero Balance can't withdraw");
		}
		if (amount > balance) {
			throw new BalanceException("Not enough balance");
		}
		this.balance-=amount;
		return this.balance;
	}
}
