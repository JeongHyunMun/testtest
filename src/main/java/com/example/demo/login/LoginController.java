package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.support.response.ApiResponse;
import com.example.demo.user.User;

@RestController
@RequestMapping("/hyeon")
public class LoginController {
	
	@Autowired
	private LoginService loginSerivce;
	
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> signUpUser(@RequestBody User user) {
		return ResponseEntity
				.ok(new ApiResponse<>(loginSerivce.signUpUser(user), user));
	}
	
}
