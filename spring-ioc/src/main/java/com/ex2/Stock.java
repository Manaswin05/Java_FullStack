package com.ex2;

public class Stock {
	public Stock() {
		System.out.println("Stock constructor");
	}
	private static Stock stk;
	public static Stock getStock() {
		System.out.println("Stock Singleton");
		if(stk==null) {
			stk = new Stock();
		}
		return stk;
	}
}
