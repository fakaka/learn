package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudEurekaClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CloudEurekaClientApplication.class).web(true).run(args);
	}
}
