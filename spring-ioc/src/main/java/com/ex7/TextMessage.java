package com.ex7;

import org.springframework.stereotype.Component;

@Component("sms")
public class TextMessage implements Message {

	public TextMessage() {
		System.out.println("Text Message comstructor");
	}
	@Override
	public void send(String to, String text) {
		System.out.println("Message sent to, " + to + ",as : " + text);
	}
}
