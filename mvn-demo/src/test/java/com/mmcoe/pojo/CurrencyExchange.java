package com.mmcoe.pojo;

public class CurrencyExchange {
	private ExchangeService service;

	public CurrencyExchange(ExchangeService service) {
		this.service = service;
	}
	public double convert(double amount) {
		return service.usdToInr(amount);
	}
}
