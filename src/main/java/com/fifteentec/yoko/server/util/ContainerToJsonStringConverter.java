package com.fifteentec.yoko.server.util;

import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ContainerToJsonStringConverter {
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
}
