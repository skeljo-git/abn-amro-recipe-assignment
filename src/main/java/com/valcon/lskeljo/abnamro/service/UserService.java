package com.valcon.lskeljo.abnamro.service;

import com.valcon.lskeljo.abnamro.exception.NotFoundException;
import com.valcon.lskeljo.abnamro.model.UserEntity;
import com.valcon.lskeljo.abnamro.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Username " + username + " not found"));
	}
}
