package test.ex6;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex6.MyLife;

public class TestMyLife {
	@Test
	public void testLife() {
		ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx6.xml");
		appCtx.getBean(MyLife.class);
		appCtx.close();
	}
}
