package com.mmcoe.stock;

public class TestStock {
	public static void main(String[] args) {
		Stock tisco = new Stock();
		
		Holder h = StockSingleton.getStock();
		h.viewQuote();
		
		Broker b = StockSingleton.getStock();
		b.getQuote();
		
		Exchange x = StockSingleton.getStock();
		x.setQuote();
		
		System.out.println(h == b);
		System.out.println(b == x);
	}
}