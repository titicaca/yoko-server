package com.fifteentec.yoko.server.util;


public class ResponseResult {
	private Boolean result;
	private String msg;
	
	public ResponseResult(Boolean result){
		this.result=result;
		this.msg = new String();
	}
	
	public ResponseResult(Boolean result, String msg){
		this.result = result;
		this.msg = msg;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
	public void setMsg(String msg){
		this.msg =msg;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public Boolean getResult(){
		return this.result;
	}
	

}
