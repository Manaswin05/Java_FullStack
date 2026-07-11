package test.ex5;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex5.Stock;

public class TestStock {
	@Test
	public void testStock(){
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx5.xml");
		
		Stock s1 = (Stock) appCtx.getBean("stk");
		Stock s2 = appCtx.getBean(Stock.class,"stk");
		System.out.println(s1==s2);
	}
}
