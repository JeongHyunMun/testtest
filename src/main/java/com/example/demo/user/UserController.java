package com.example.demo.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.support.response.ApiResponse;

@RestController
@RequestMapping("/hyeon/user")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
//	/**
//	 * 사용자 추가
//	 * @param user
//	 * @return
//	 */
//	@PostMapping
//	public void addUser(@RequestBody User user) {
//	}
//
//	/**
//	 * 사용자 삭제
//	 * @param user
//	 * @return
//	 */
//	@PostMapping("/{userNo}")
//	@Transactional
//	public void deleteUser(@RequestBody User user) {
//	}
//
//	/**
//	 * 사용자 수정
//	 * @param user
//	 * @return
//	 */
//	@PostMapping("/{userNo}")
//	@Transactional
//	public void updateUser(@RequestBody User user) {
//	}
	
	/**
	 * 사용자 조회
	 * @param user
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<User>> inqueryUser(@RequestBody User user) {
		return ResponseEntity
				.ok(new ApiResponse<>(userSerivce.addUser(user), user));
	}
	
}
