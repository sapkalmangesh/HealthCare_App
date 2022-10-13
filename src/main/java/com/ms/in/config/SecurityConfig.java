package com.ms.in.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ms.in.service.constant.UserRoles;
import com.ms.in.util.MyMailUtil;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private MyMailUtil mailUtil;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	} 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//.antMatchers("/patient/register","/patient/save").permitAll()
		//.antMatchers("/patient/all").hasAuthority(UserRoles.ADMIN.name())
		//.antMatchers("/doctor/**").hasAuthority(UserRoles.ADMIN.name())
		
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.defaultSuccessUrl("/spec/all",true)
		
		.and()
		.logout();
	}


}
