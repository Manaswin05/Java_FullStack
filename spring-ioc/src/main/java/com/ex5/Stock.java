package com.ex5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("stk")
@Scope("singleton")
public class Stock {
	public Stock() {
		System.out.println("Stock constructor");
	}
	private static Stock stk;
//	@Bean
	public static Stock getStock() {
		System.out.println("Stock Singleton");
		if(stk==null) {
			stk = new Stock();
		}
		return stk;
	}
}
