package com.ex8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchenageConfig {
	@Bean("exchange")
	public ExchangeService getService() {
		System.out.println("Contracting exchange servers");
		return new ExchangeService();
	}
}
