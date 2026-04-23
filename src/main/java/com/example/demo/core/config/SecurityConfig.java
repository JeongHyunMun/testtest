package com.example.demo.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.core.support.security.CustomLogoutHandler;
import com.example.demo.core.support.security.CustomLogoutSuccessHandler;
import com.example.demo.core.support.security.LoginFailureHandler;
import com.example.demo.core.support.security.LoginSuccessHandler;
import com.example.demo.core.support.security.jwt.JwtAccessDeniedHandler;
import com.example.demo.core.support.security.jwt.JwtAutenticationEntryPoint;
import com.example.demo.core.support.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@MapperScan("com.example.demo")
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailureHandler loginFailureHandler;
	
	@Autowired
	private CustomLogoutHandler logoutHandler;
	
	@Autowired
	private CustomLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private JwtAutenticationEntryPoint jwtAutenticationEntryPoint;
	
	@Autowired
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.exceptionHandling(exceptions -> exceptions
					.authenticationEntryPoint(jwtAutenticationEntryPoint)
					.accessDeniedHandler(jwtAccessDeniedHandler)
					)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/index.html",
						"/assets/**",
						"/css/**", 
						"/js/**", 
						"/imgages/**",
						"/hyeon",
						"/hyeon/refresh",
						"/hyeon/signup"
						).permitAll()
				.requestMatchers("/hyeon/**").authenticated()
				.anyRequest().permitAll()
				)
			.formLogin(form -> form
				.usernameParameter("user_id")
				.passwordParameter("user_password")
				.loginPage("/hyeon")	
			    .loginProcessingUrl("/hyeon/login") // 로그인 POST 요청을 처리
				.failureUrl("/?error")
				.permitAll()
				.successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
			)
			.logout(logout -> logout
				.logoutUrl("/hyeon/logout")
				.addLogoutHandler(logoutHandler)
				.logoutSuccessHandler(logoutSuccessHandler)
				.permitAll()
			);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
