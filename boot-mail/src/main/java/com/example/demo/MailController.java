package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/send/{msg}")
	public String sendSimpleMail(@PathVariable("msg") String text) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("vsmj012@163.com");
		mailMessage.setTo("vsmj012@163.com");
		mailMessage.setSubject("无");
		mailMessage.setText(text);
		mailSender.send(mailMessage);
		System.out.println("send");
		return "666";
	}
}
