package com.fifteentec.yoko.server.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String string) {
		super("could not find user '" + string + "'.");
	}
	
	public UserNotFoundException(Long id) {
		super("could not find user id'" + id + "'.");
	}
}
