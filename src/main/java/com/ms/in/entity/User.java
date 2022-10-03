package com.ms.in.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user_tab")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "user_id_col")
	private Long id;
	
	@Column(name= "user_displayName_col")
	private String displayName;
	
	@Column(name= "user_username_col")
	private String username;
	
	@Column(name= "user_password_col")
	private String password;
	
	@Column(name= "user_Role_col")
	private String role;

}
