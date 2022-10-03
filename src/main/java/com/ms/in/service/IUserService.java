package com.ms.in.service;

import java.util.Optional;

import com.ms.in.entity.User;

public interface IUserService {
	
	public Long saveUser(User user);
	
	Optional<User> findByUsername(String username);

}
