package com.ms.in.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.in.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
