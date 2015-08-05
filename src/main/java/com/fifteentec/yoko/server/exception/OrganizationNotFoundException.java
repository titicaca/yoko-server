package com.fifteentec.yoko.server.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrganizationNotFoundException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OrganizationNotFoundException(String id) {
		super("could not find orgnization '" + id + "'.");
	}
	
	public OrganizationNotFoundException(Long id) {
		super("could not find orgnization '" + id + "'.");
	}
}