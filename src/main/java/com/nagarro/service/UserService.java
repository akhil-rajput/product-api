package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.dao.UserRepo;
import com.nagarro.model.User;

@Component
public class UserService {

	@Autowired
	UserRepo userRepo;

	public User getUser(String username) {

		User user = userRepo.findByUsername(username);
		return user;
	}

	public int countMembers() {
		int countMembers = userRepo.findCountOUsers();
		return countMembers;
	}

}
