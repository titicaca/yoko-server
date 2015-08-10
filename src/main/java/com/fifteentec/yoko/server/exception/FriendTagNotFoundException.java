package com.fifteentec.yoko.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FriendTagNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FriendTagNotFoundException(Long id) {
		super("could not find FriendTag '" + id + "'.");
	}
}
