package com.example.demo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public HairpinResponse say(HairpinMessage message) throws Exception {
		System.out.println(message.getName());
		return new HairpinResponse("Welcome, " + message.getName() + "!");
	}
}