package com.mmcoe.bank;

public class TestAccounts {
	public static void main(String[] args) throws BalanceException {
		Accounts ac = new Accounts(5000);
		System.out.println(ac);
		ac.withdraw(3000);
		System.out.println(ac);
		ac.withdraw(7000);
		System.out.println(ac);

		ac.deposit(2000);
		System.out.println(ac);
		ac.deposit(5000);
		System.out.println(ac);
		try {
			ac.withdraw(15000);
		} catch (BalanceException e) {
			System.out.println(e.getMessage());
		}
	}
}