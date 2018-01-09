package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import reactor.core.publisher.Flux;

@Configuration
public class RouterFunctionConfiguration {

	@Bean
	@Autowired
	public RouterFunction<ServerResponse> personAll(UserRepository userRepository) {
		return RouterFunctions.route(RequestPredicates.GET("/users2"), request -> {
			List<User> allUser = userRepository.findAll();
			Flux<User> userFlux = Flux.fromIterable(allUser);
			return ServerResponse.ok().body(userFlux, User.class);
		});
	}

}
