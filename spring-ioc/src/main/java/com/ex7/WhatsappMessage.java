package com.ex7;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("wap")
@Lazy
public class WhatsappMessage implements Message {

	public WhatsappMessage() {
		System.out.println("Whatsapp Message comstructor");
	}
	@Override
	public void send(String to, String text) {
		System.out.println("Whatsapp sent to, " + to + ",as : " + text);
	}
}
