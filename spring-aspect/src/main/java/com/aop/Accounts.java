package com.aop;

public class Accounts {
	private double balance;
	public double deposit(double amount) {
		balance+=amount;
		System.out.println("Deposited Amount " + balance);
		return balance;
	}
	public double withdraw(double amount) throws BalanceException {
		if(amount<=balance) {
		balance-=amount;
		System.out.println("Withdraw amount: " + amount);
		}else {
			throw new BalanceException("Insufficient Balance");
		}
		return balance;
	}
	public void summary() {
		System.out.println(balance);
	}
	public void getBalance() {
		System.out.println(balance);
	}
}
