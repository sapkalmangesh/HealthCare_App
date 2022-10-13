package com.ms.in.service.Impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ms.in.entity.User;
import com.ms.in.repo.UserRepository;
import com.ms.in.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Long saveUser(User user) {
		// read Password
		String pwd = user.getPassword();

		// encode password
		String encPwd = passwordEncoder.encode(pwd);

		// Set back to object
		user.setPassword(encPwd);

		Long id = repo.save(user).getId();

		return id;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
					throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		if(!opt.isPresent()) {
			throw new UsernameNotFoundException(username);
		}else {
			User user = opt.get();
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), 
					user.getPassword(), 
					Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
		}
	}

}
