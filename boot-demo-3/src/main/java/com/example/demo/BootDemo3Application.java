package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BootDemo3Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BootDemo3Application.class).web(true).run(args);
	}
}
