package test.ex9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ex9.TemperatureConverter;

public class testTemperature {
	@Test
	public void testFtoc() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-ctx9.xml");
		TemperatureConverter tc = (TemperatureConverter) ctx.getBean("temp");
		int c = tc.ftoc(102);
		Assertions.assertEquals(38,	c);
	}
	@Test
	public void testcTof() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-ctx9.xml");
		TemperatureConverter tc = (TemperatureConverter) ctx.getBean("temp");
		int c = tc.ctof(36);
		Assertions.assertEquals(68,	c);
	}
}
