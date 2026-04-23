package com.example.demo.core.support.response;

public class ResultException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private final ResultCode resultCode;
	
	public ResultException(ResultCode resultCode) {
		super(resultCode.getMessage());
		this.resultCode = resultCode;
	}
	
	public ResultException(ResultCode resultCode, String resultMessage) {
		super(resultMessage);
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

}
