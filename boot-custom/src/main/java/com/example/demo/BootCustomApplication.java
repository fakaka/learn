package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.autocinfigure.ExampleService;

@RestController
@SpringBootApplication
public class BootCustomApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootCustomApplication.class, args);
	}

	@Autowired
	private ExampleService exampleService;

	@GetMapping("/input/{word}")
	public String input(@PathVariable("word") String word) {
		return exampleService.wrap(word);
	}

}
