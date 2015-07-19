package com.fifteentec.yoko.server.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class BaseModel implements Serializable{
	private String createdtime;
	private int status;		
	
	public BaseModel(){
		setCreatedtime(System.currentTimeMillis());
	}

	public String getCreatedtime() {
		return createdtime;
	}

	private void setCreatedtime(long createdtimestamp){
    	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    	Long time=new Long(createdtimestamp);

    	String d = format.format(time);
    	 
    	this.createdtime=d;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	

}
