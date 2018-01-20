package com.example.demo.controller;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 正常 MVC 模式
	 */
	@GetMapping("/")
	public String hello() {
		return "hello!";
	}

	/**
	 * 新增一个 User
	 */
	@PostMapping("/user")
	public Mono<Void> add(@RequestBody Publisher<User> User) {
		return userRepository.insert(User).then();
	}

	/**
	 * 根据 ID 查询 User
	 */
	@GetMapping("/user/{id}")
	public Mono<User> getById(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	/**
	 * 查询所有 User
	 */
	@GetMapping("/user/list")
	public Flux<User> list() {
		return userRepository.findAll();
	}

	/**
	 * 删除指定 User
	 */
	@DeleteMapping("/user/{id}")
	public Mono<Void> delete(@PathVariable Long id) {
		return userRepository.deleteById(id).then();
	}

}
