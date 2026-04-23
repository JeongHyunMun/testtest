package com.example.demo.core.support.security.auth;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private final StringRedisTemplate stringRedisTemplate;

	private static final long REFRESH_TOKEN_TTL = 60 * 60 * 24 * 7; // 7일

	public void saveRefreshToken(String username, String refreshToken) {
		stringRedisTemplate.opsForValue().set("RT:" + username, refreshToken, REFRESH_TOKEN_TTL, TimeUnit.SECONDS);
	}

	public String getRefreshToken(String username) {
		return stringRedisTemplate.opsForValue().get("RT:" + username);
	}

	public void deleteRefreshToken(String username) {
		stringRedisTemplate.delete("RT:" + username);
	}

}
