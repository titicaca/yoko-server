package com.fifteentec.yoko.server.util;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonConverterUtil {
	public static <T> String convertSetToJsonString(Set<T> s){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = new String();
		try {
			json = ow.writeValueAsString(s);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format("{list:%s}", json);
	}
	
	public static ResponseEntity<String> convertResultToResponseEntity(ResponseResult r) {
		HttpHeaders headers = new HttpHeaders();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = new String();
		try {
			json = ow.writeValueAsString(r);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(r.getResult() == true){
			return new ResponseEntity<String>(json,  headers, HttpStatus.OK);			
		}else{
			return new ResponseEntity<String>(json,  headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	public static <T> String convertObjToString(T obj) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = new String();
		try {
			json = ow.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return json;
	}
}
