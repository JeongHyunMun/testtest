package com.example.demo.core.support.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserAuthenticationMapper loginMapper;

	@Override
    public UserAuthentication loadUserByUsername(String user_id) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
		User user = loginMapper.getUserAccount(user_id);
		
        if (user == null){
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
    	
        return new UserAuthentication(user);
    }
	
}
