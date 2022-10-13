package com.ms.in.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ms.in.entity.User;
import com.ms.in.service.IUserService;
import com.ms.in.service.constant.UserRoles;
import com.ms.in.util.MyMailUtil;
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

	@Autowired
	private MyMailUtil mailUtil;

	@Override
	public void run(String... args) throws Exception {
		if (!userService.findByUsername(username).isPresent()) {
			String pwd = userUtil.genPwd();
			User user = new User();
			user.setDisplayName(displyName);
			user.setUsername(username);
			user.setPassword(pwd);
			user.setRole(UserRoles.ADMIN.name());
			Long genId = userService.saveUser(user);
			if (genId != null) {
				new Thread(new Runnable() {
					public void run() {
						String text = "Username is " + username + " , Password is " + pwd;
						mailUtil.send(username, "ADMIN ADDED", text);
					}
				}).start();
			}

		}

	}

}
