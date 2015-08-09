package com.fifteentec.yoko.server.model;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Result {
	private Boolean result;
	private String msg;
	
	public Result(Boolean result){
		this.result=result;
		this.msg = new String();
	}
	
	public Result(Boolean result, String msg){
		this.result = result;
		this.msg = msg;
	}

	public ResponseEntity<String> getResponseResult() {
		HttpHeaders headers = new HttpHeaders();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = new String();
		try {
			json = ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(json,  headers, HttpStatus.OK);
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	

}
