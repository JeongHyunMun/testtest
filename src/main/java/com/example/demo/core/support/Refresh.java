package com.example.demo.core.support;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.support.response.ApiResponse;
import com.example.demo.core.support.response.ResultCode;

@RestController
@RequestMapping("/hyeon")
public class Refresh {
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<Authentication>> refresh() {
		Authentication userInfo = null;
		try	{
			userInfo = SecurityContextHolder.getContext().getAuthentication();
		} catch (Exception e) {
			return ResponseEntity
					.ok(new ApiResponse<>(ResultCode.BAD_REQUEST, userInfo)); // 나중에 시간여유가 되는 경우 세분화
		}
		return  ResponseEntity
				.ok(new ApiResponse<>(ResultCode.SUCCESS, userInfo));
	}
}
