package com.example.demo.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User{
	/**
	 * 
	 */
	private String user_no;
	private String user_id;
	private String user_password;
	private String user_name;
	private String role_name;
	private String login_fail_count;
	
}
