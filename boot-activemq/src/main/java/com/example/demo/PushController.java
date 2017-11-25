package com.example.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushController {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	JmsMessagingTemplate jmsMessagingTemplate;

	@RequestMapping("/push/{msg}")
	public void push(@PathVariable("msg") String msg) {
		jmsTemplate.send("queue", (MessageCreator) session -> session.createTextMessage(msg));
	}

	@RequestMapping("/push2/{msg}")
	public void push2(@PathVariable("msg") String msg) {
		jmsMessagingTemplate.convertAndSend(new ActiveMQQueue("queue"), "msg");
	}

}
