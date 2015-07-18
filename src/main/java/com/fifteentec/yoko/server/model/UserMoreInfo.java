package com.fifteentec.yoko.server.model;

import java.text.SimpleDateFormat;

public class UserMoreInfo {
	private int user_id;
	private int sex; 
    private String email;
    private String qq;
    private String wechat;
    private String createdtime;

    
    public UserMoreInfo(){
    	setCreatedtime(System.currentTimeMillis());
    }
    
    public int getUser_id(){
    	return user_id;
    }
    
    public void setUser_id(int user_id){
    	this.user_id=user_id;
    }
    
    public int getSex(){
    	return sex;
    }
    
    public void setSex(int sex){
    	this.sex=sex;
    }
    
    public String getEmail(){
    	return email;
    }
    
    public void setEmail(String email){
    	this.email=email;
    }
    
    public String getQq(){
    	return qq;
    }
    
    public void setQq(String qq){
    	this.qq=qq;
    }
    
    public String getWechat(){
    	return wechat;
    }
    
    public void setWechat(String wechat){
    	this.wechat=wechat;
    }
    
    public String getCreatedtime(){
    	return createdtime;
    }
   
    private void setCreatedtime(long createdtimestamp){
    	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

    	Long time=new Long(createdtimestamp);

    	String d = format.format(time);
    	 
    	this.createdtime=d;
    }
    
}
