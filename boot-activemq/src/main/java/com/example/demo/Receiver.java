package com.example.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	@JmsListener(destination = "queue")
	public void listener(String message) {
		System.out.println("consumer " + message);
	}
}
