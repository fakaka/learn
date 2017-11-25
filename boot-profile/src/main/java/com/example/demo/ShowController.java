package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowController {

	@Value("${age}")
	private String age;

	@Autowired
	private Environment env;

	@RequestMapping("/testProfile")
	public String testProfile() {
		return env.getProperty("age");
	}

	@RequestMapping("/age")
	public void name() {
		System.out.println(age);
	}
}
