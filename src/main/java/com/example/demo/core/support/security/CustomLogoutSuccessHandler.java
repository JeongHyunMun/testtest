package com.example.demo.core.support.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.core.support.GlobalConstants;
import com.example.demo.core.support.response.ApiResponse;
import com.example.demo.core.support.response.ResultCode;
import com.example.demo.core.support.security.auth.RefreshTokenService;
import com.example.demo.core.support.security.auth.UserAuthentication;
import com.example.demo.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{
	
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private RefreshTokenService refreshTokenService;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {
		
			ApiResponse<?> loginResponse = new ApiResponse<> (
					ResultCode.SUCCESS,
					null
			);
			
			// 로그아웃 시 Refresh Token 제거 
			String username = authentication.getName();
			refreshTokenService.deleteRefreshToken(username);
			
			response.setContentType("application/json;charset=UTF-8");
			
			objectMapper.writeValue(response.getWriter(), loginResponse);
		
	}

}
