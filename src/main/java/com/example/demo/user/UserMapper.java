package com.example.demo.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	Object inqueryUser(User user);
	
}

