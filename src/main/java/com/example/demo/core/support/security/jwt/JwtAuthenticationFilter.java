package com.example.demo.core.support.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.core.support.security.auth.RefreshTokenService;
import com.example.demo.core.support.security.auth.UserAuthentication;
import com.example.demo.core.support.security.auth.UserAuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private final JwtTokenProvider tokenProvider;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	UserAuthenticationService userAuthenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        String accessToken = resolveAccessToken(request);
        
        // 1) Access Token이 있고 유효하면 인증 처리
        if (accessToken != null) {
            try {
            	Claims claims = tokenProvider.getClaims(accessToken); // ExpiredJwtException 발생 가능
                setAuthentication(claims.getSubject());
                filterChain.doFilter(request, response);
                return;

            } catch (ExpiredJwtException e) {
                // 💡 Access Token 만료 → 아래에서 Refresh Token 검증
            }
            
        }
        
        // 2) Refresh Token 추출
        String refreshToken = resolveRefreshToken(request);

        if (accessToken != null && tokenProvider.validateToken(accessToken)) {
            Authentication auth = tokenProvider.getAuthentication(accessToken);
            UserAuthentication principal = (UserAuthentication) auth.getPrincipal();
            String username = principal.getUser().getUser_id();
            
            String saved = refreshTokenService.getRefreshToken(username);
            
            if (refreshToken.equals(saved)) {
            	// 3) 새 Access Token 발급
                String newAccessToken =
                        tokenProvider.createAccessToken(username, auth.getAuthorities());

                // 4) 응답 헤더에 넣어주기
                response.setHeader("Authorization", "Bearer " + newAccessToken);

                // 5) 인증 객체 설정
                setAuthentication(username);
                
                filterChain.doFilter(request, response);
                return;
            }
            
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
    
    private void setAuthentication(String username) {
        UserAuthentication user = userAuthenticationService.loadUserByUsername(username);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
    
    private String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("RefreshToken");
    }
}