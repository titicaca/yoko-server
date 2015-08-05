package com.fifteentec.yoko.server.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ActivityNotFoundException(Long activity_id) {
		super("could not find activity '" + activity_id + "'.");
	}
}
