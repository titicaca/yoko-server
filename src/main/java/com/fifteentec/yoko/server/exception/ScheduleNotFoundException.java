package com.fifteentec.yoko.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScheduleNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScheduleNotFoundException(Long schedule_id) {
		super("could not find schedule id '" + schedule_id + "'.");
	}
}
