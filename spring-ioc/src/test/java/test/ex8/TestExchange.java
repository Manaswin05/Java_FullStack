package test.ex8;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex8.ExchangeService;
import com.ex8.ExchenageConfig;

public class TestExchange {

	@Test
	public void testConfig() {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(ExchenageConfig.class);
		ExchangeService ex = (ExchangeService) ctx.getBean("exchange");
		ex.convert();
	}
}
