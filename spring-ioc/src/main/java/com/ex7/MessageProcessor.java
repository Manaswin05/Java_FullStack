package com.ex7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

@Repository("mp")
public class MessageProcessor {
	@Autowired
	private ApplicationContext appctx;
	public MessageProcessor() {
		System.out.println("Msg Processor Constructor");
	}
	public void process(String type, String to,String text) {
		Message msg = (Message) appctx.getBean(type);
		msg.send(to, text);
	}
}
