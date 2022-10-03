package com.ms.in.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.in.entity.User;
import com.ms.in.repo.UserRepository;
import com.ms.in.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository repo;

	@Override
	public Long saveUser(User user) {
		Long id = repo.save(user).getId();
		
		return id;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

}
