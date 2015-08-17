package com.fifteentec.yoko.server.util;


public class PushMessage <T>{
	private int action;
	private T body;
	
	public PushMessage(int action,T body) {
		this.action = action;
		this.body = body;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
