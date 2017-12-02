package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.example.config.RuleConfiguration;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "eureka-client", configuration = RuleConfiguration.class)
public class CloudConsumerRibbonRuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConsumerRibbonRuleApplication.class, args);
	}
}
