package test.ex1;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex1.Hello;

public class TestHello {
	@Test
	public void testMessage() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("ex1-ctx.xml");
		
		Hello h1 = (Hello) appCtx.getBean("hi");
		System.out.println(h1.getMessage());
	}
}
