package com.example.demo.core.support.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.demo.core.support.security.auth.UserAuthentication;
import com.example.demo.core.support.security.auth.UserAuthenticationService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	
	@Autowired
	UserAuthenticationService userAuthenticationService;
	
	private final JwtProperties jwtProperties;
    private final Key key;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
        log.info("jwt 키 : " + jwtProperties.getAccessTokenValidity());
        log.info("jwt 키 : " + jwtProperties.getRefreshTokenValidity());
        log.info("jwt 키 : " + jwtProperties.getSecret());
        
        log.info("jwt 키 : " + key);
        
    }

    // 토큰 생성
    public String createAccessToken(String username, Collection<? extends GrantedAuthority> authorities) {

        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtProperties.getAccessTokenValidity());

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiry)
                .compact();
    }
    
    public String createRefreshToken(String username) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtProperties.getRefreshTokenValidity());
        
        return Jwts.builder()
        		.setSubject(username)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 인증 정보 추출
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {

        Claims claims = Jwts.parserBuilder()
        		.setSigningKey(key)
        		.build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        
        UserAuthentication authentication = userAuthenticationService.loadUserByUsername(username);
 

        return new UsernamePasswordAuthenticationToken(authentication, "", authentication.getAuthorities());
    }
    
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
