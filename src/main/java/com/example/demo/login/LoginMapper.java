package com.example.demo.login;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.user.User;

@Mapper
public interface LoginMapper {
	
	void signUpUser(User user);

}
