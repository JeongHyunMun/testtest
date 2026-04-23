package com.example.demo.core.support.security.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.user.User;

public record UserAuthenticationResponse(
		String accessToken,
		String refreshToken,
		User user,
		Collection<? extends GrantedAuthority> roles) {
}
