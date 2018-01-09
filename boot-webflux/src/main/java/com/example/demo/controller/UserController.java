package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAll() {

		return userRepository.findAll();
	}

}
