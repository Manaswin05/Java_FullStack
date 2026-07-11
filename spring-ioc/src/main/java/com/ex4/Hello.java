package com.ex4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("hi")
public class Hello {
	private String message;
	public Hello() {
		System.out.println("Default Constructor");
	}
	public Hello(String message) {
		this.message = message;
		System.out.println("Parameterised constructor");
	}
	public String getMessage() {
		return message;
	}
	@Value("bonjour Spring")
	public void setMessage(String message) {
		this.message = message;
		System.out.println("Setter method");
	}		
}
