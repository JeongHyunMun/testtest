package com.example.demo.core.support.security.auth;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.user.User;

@Mapper
public interface UserAuthenticationMapper {
	
	User getUserAccount(String user_id);

}
