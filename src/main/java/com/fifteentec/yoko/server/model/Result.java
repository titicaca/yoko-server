package com.fifteentec.yoko.server.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Result {
	private Boolean result;
	
	public Result(Boolean result){
		this.result=result;
	}

	public ResponseEntity<Boolean> getResponseResult() {
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Boolean>(result,  headers, HttpStatus.OK);
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	

}
