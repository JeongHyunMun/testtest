package com.example.demo.core.support.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultCode {
	
	/**
	 * 성공
	 */
	SUCCESS("0000", "성공"),
	/**
	 * 잘못된 요청
	 */
	BAD_REQUEST("0400", "잘못된 요청입니다."),
	/**
	 * 로그인 필요
	 */
	UNAUTHORIZED("0401", "로그인이 필요합니다."),
	/**
	 * 접근 실패
	 */
	FORBIDDEN("0403", "접근할 수 없습니다."),
	/**
	 * 리소스 확인 불가
	 */
	NOT_FOUND("0404", "요청받은 리소스를 찾을 수 없습니다."),
	/**
	 * 예기치 않은 오류
	 */
	INTERNAL_SERVER_ERROR("0500", "서버 내부에서 예기치 않은 오류가 발생하였습니다.");

	private final String code;
	private final String message;
	
}
