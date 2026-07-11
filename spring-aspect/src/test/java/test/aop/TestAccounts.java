package test.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aop.Accounts;
import com.aop.BalanceException;

public class TestAccounts {
	@Test
	public void testAspects() {
		ApplicationContext context = new ClassPathXmlApplicationContext("app-ctx.xml");
		
		Accounts a1 = context.getBean(Accounts.class);
		a1.deposit(1000);
		a1.summary();
		a1.getBalance();
		try {
			a1.withdraw(1000);
		}catch(BalanceException e) {
			e.printStackTrace();
		}
		
	}
}