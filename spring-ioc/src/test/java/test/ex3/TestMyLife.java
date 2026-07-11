package test.ex3;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex3.MyLife;

public class TestMyLife {
	@Test
	public void testLife() {
		ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("ex3-ctx.xml");
		appCtx.getBean(MyLife.class);
		appCtx.close();
	}
}
