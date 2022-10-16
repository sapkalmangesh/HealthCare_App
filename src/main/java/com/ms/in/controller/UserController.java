package com.ms.in.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ms.in.entity.User;
import com.ms.in.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService service;
	
	@GetMapping("/login")
	public String viewLogin() {
		return "UserLogin";
	}
	
	@GetMapping("/setup")
	public String user(HttpSession session, Principal p) {
		
		// To get currunt user name
		String username = p.getName();
		
		// to get full user object
		User user = service.findByUsername(username).get();
		
		// set user full object into Session Object
		
		session.setAttribute("userOb", user);
		
		return "UserHome";
		
	}

}
