package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.SerialNumberGenerator;
import com.example.demo.core.support.SerialCode;
import com.example.demo.core.support.response.ResultCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public ResultCode addUser(User user) throws UsernameNotFoundException {
		try {
	        user.setUser_no(SerialNumberGenerator.generate(SerialCode.USERNO));
	        user.setUser_password(passwordEncoder.encode(user.getUser_password()));
	        user.setRole_name("USER");
	        userMapper.addUser(user);
		} catch (Exception e) {
			return ResultCode.BAD_REQUEST; // 나중에 시간여유가 되는 경우 세분화
		}
		return ResultCode.SUCCESS;
	}
	
	
}
