package com.example.demo.core.support;

import lombok.Getter;

@Getter
public enum SerialCode {
	/*
	 * 고유번호
	 */
	USERNO("01"),
	BOARDNO("02");
	
	private final String code;
	
	SerialCode(String code) {
		this.code = code;
	}
}
