package com.aop;

public class BalanceException extends Exception {
	private String message;
	public BalanceException(String message) { 
		this.message = message;
		System.out.println(message);
	}
	public BalanceException(String message) {
		super(message);
	}

}
