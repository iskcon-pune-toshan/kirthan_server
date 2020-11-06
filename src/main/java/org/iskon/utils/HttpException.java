package org.iskon.utils;

import org.springframework.http.HttpStatus;

public class HttpException extends Exception{

	private static final long serialVersionUID = 1L;

	private HttpStatus respCode;
	private String message;

	public HttpException( String message,HttpStatus errCode) {
		super(message);
		this.respCode = errCode;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public HttpStatus getStatusCode() {
		return this.respCode;
	}
}
