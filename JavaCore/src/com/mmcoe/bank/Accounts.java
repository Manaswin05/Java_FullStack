package com.mmcoe.bank;

public class Accounts {
	private double balance;
	private double overdraft;
	
	public Accounts() {

	}

	public Accounts(double balance) {
		this.balance = balance;
		overdraft = 10000;
	}

	@Override
	public String toString() {
		return "Balance = " + balance + " Overdraft = " + overdraft;
	}

	public void deposit(double amount) {
		overdraft += amount;
		if(overdraft > 10000) {
			balance += (overdraft-10000);
			overdraft = 10000;
		}
	}
	
	public void withdraw(double amount) throws BalanceException {
		if(amount <= (balance + overdraft)) {
			balance-= amount;
			if(balance < 0) {
				overdraft += balance;
				balance = 0;
			}	
		}else {
			throw new BalanceException("Insufficient Balance!");
		}
	}
}