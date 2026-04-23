package com.example.demo.core.support.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) {
		request.getSession().invalidate();
		
		clearCookie(response, CookieName.JSESSIONID.name(), CookieName.JSESSIONID.path());
		
		// jwt 토큰 개발 후 적용
//		clearCookie(response, CookieName.ACCESS_TOKEN.name(), CookieName.ACCESS_TOKEN.path());
//		clearCookie(response, CookieName.REFRESH_TOKEN.name(), CookieName.REFRESH_TOKEN.path());
		
	}
	
	public static void clearCookie(HttpServletResponse res, String name, String path) {
		res.addCookie(new Cookie(name, null) {
			{
				setPath(path);
				setMaxAge(0);
			}
		});
	}
	
	public enum CookieName {
		JSESSIONID("/"),
		ACCESS_TOKEN("/hyeon"),
		REFRESH_TOKEN("/hyeon");
		
	    private final String path;
	
	    CookieName(String path) { 
	    	this.path = path; 
	    	}
	    
	    public String path() { 
	    	return path; 
	    	}
	}

}
