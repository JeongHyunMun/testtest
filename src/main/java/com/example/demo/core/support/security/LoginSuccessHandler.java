package com.example.demo.core.support.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.core.support.response.ApiResponse;
import com.example.demo.core.support.response.ResultCode;
import com.example.demo.core.support.security.auth.RefreshTokenService;
import com.example.demo.core.support.security.auth.UserAuthentication;
import com.example.demo.core.support.security.auth.UserAuthenticationResponse;
import com.example.demo.core.support.security.jwt.JwtTokenProvider;
import com.example.demo.user.User;
import com.example.demo.user.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		User user = ((UserAuthentication) authentication.getPrincipal()).getUser();
		
		String accessToken = tokenProvider.createAccessToken(user.getUser_id(), authentication.getAuthorities());
        String refreshToken = tokenProvider.createRefreshToken(user.getUser_id());

        refreshTokenService.saveRefreshToken(user.getUser_id(), refreshToken);
		
//		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json;charset=UTF-8");
		 
		 UserAuthenticationResponse userAuthenticationResponse = new UserAuthenticationResponse (
					accessToken,
				 	refreshToken,
					user,
					authentication.getAuthorities()
			);
		
		objectMapper.writeValue(response.getWriter(), userAuthenticationResponse);
	}

}
