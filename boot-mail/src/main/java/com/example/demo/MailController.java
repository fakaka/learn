package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/send")
	public String sendSimpleMail(String text) {
		System.out.println("send");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("vsmj012@163.com");
		mailMessage.setTo("vsmj012@sina.com");
		mailMessage.setSubject("无");
		mailMessage.setText("555");
		mailSender.send(mailMessage);
		return "666";
	}
}
