package com.ms.in.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ms.in.entity.User;
import com.ms.in.service.IUserService;
import com.ms.in.service.constant.UserRoles;
import com.ms.in.util.UserUtil;

@Component
public class MasterAccountSetupRunner implements CommandLineRunner {
	
	@Value("${master.user.name}")
	private String displyName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;

	@Override
	public void run(String... args) throws Exception {
		if(!userService.findByUsername(username).isPresent()) {
			User user = new User();
			user.setDisplayName(displyName);
			user.setUsername(username);
			user.setPassword(userUtil.genPwd());
			user.setRole(UserRoles.ADMIN.name());
			userService.saveUser(user);
			
			//TODO email part is pendinin
		}
		
		

	}

}
