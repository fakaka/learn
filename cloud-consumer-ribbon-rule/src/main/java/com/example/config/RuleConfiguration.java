package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Component
public class RuleConfiguration {

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();
	}

}
