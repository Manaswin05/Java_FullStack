package test.ex7;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ex7.MessageProcessor;

public class TestMessageProcessor {
	@Test
	public void testProcess() {
		ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("app-ctx7.xml");
		MessageProcessor mp = appCtx.getBean(MessageProcessor.class,"mp");
		mp.process("sms", "9820ZUBAIR", "Hello Zubair");
		mp.process("email", "zubair@gmail.com", "Hola Zubair");
		mp.process("wap", "@zubair", "Ciao Zubair");
	}
}
