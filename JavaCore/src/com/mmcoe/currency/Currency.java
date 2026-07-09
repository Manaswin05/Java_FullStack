package com.mmcoe.currency;

public interface Currency {
	double dollarValue();
	
	static double convert(Currency source, Currency target, double amount) {
		return target.dollarValue() / source.dollarValue()*amount;
	}
	Currency USD = ()->1.0;
	Currency INR = ()->95.50;
	Currency AED = ()->3.75;
}
