package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.domain.UserMapper;

@RestController
// @RequestMapping(value = "/user") // 通过这里配置使下面的映射都在/user下，可去除
public class UserController {

	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
	public User getUser(@PathVariable("name") String name) {
		return userMapper.findByName(name);
	}

}