package com.mmcoe.stock;

interface Holder{
	public void viewQuote();
}

interface Broker{
	public void getQuote();
}

interface Exchange{
	public void setQuote();
}

public class Stock implements Exchange, Broker, Holder {
	public void setQuote() {
		System.out.println("Set Quote");
	}
	
	public void viewQuote() {
		System.out.println("View Quote");
	}
	
	public void getQuote() {
		System.out.println("Get Quote");
	}
}
