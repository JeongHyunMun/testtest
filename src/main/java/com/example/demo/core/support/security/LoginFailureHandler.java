package com.example.demo.core.support.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.demo.core.support.GlobalConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		로그인 5회 실패 시 계정잠금 코드 개발 예정
//		String userId = request.getParameter(GlobalConstants.LOGIN_USERNAME);
//		

//		response.sendRedirect(request.getContextPath() + GlobalConstants.LOGIN_URL + "?error");
		
		response.sendRedirect(request.getContextPath() + GlobalConstants.LOGIN_URL);
		
	}

}
