package com.ex7;

import org.springframework.stereotype.Component;

@Component("email")
public class EmailMessage implements Message {

	public EmailMessage() {
		System.out.println("Email Message comstructor");
	}
	@Override
	public void send(String to, String text) {
		System.out.println("Email sent to, " + to + ",as : " + text);
	}
}
