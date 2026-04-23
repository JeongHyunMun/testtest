package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.SerialNumberGenerator;
import com.example.demo.core.support.SerialCode;
import com.example.demo.core.support.response.ResultCode;
import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	@Autowired
    private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private LoginMapper loginMapper;
	
	public ResultCode signUpUser(User user) throws UsernameNotFoundException {
		try {
			/*
			 * user_no 셋팅
			 * format : "yyyyMMddHHmmss" + 랜덤 8자리 값
			 */
	        user.setUser_no(SerialNumberGenerator.generate(SerialCode.USERNO));
	        user.setUser_password(passwordEncoder.encode(user.getUser_password()));
	        user.setRole_name("USER");
	        loginMapper.signUpUser(user);
		} catch (Exception e) {
			return ResultCode.BAD_REQUEST; // 나중에 시간여유가 되는 경우 세분화
		}
		return ResultCode.SUCCESS;
	}
	
	
}
