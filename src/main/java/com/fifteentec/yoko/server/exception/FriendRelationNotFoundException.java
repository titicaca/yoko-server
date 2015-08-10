package com.fifteentec.yoko.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FriendRelationNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FriendRelationNotFoundException(Long user_id , Long friend_id) {
		super("could not find user friend relation [" + user_id + ", " + friend_id + ".");
	}
}
