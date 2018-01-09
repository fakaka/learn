package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public class UserRepository {

	public List<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		User user1 = new User("1", "mj");
		User user2 = new User("2", "lyt");
		users.add(user1);
		users.add(user2);
		return users;
	}
}
