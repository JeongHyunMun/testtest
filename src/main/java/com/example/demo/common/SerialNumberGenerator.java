package com.example.demo.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

import com.example.demo.core.support.SerialCode;

public class SerialNumberGenerator {
	
	/*/
	 * 고유번호 생성
	 */
	public static String generate(SerialCode prefix) {
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        StringBuilder randPart = new StringBuilder();
        for (int i = 0; i < 8; i++) {
        	randPart.append(ThreadLocalRandom.current().nextInt(10));
		}
        return prefix.getCode() + today + randPart.toString();
	}

}
