package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BootDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(BootDemo2Application.class, args);
	}
}
