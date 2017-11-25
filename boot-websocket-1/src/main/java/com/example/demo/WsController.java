package com.example.demo;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		if (principal.getName().equals("mj")) {
			simpMessagingTemplate.convertAndSendToUser("lyt", "/queue/notifications", principal.getName() + "-send: " + msg);
		} else {
			simpMessagingTemplate.convertAndSendToUser("mj", "/queue/notifications", principal.getName() + "-send: " + msg);
		}
	}
}