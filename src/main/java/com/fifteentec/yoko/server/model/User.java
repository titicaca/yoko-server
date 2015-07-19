package com.fifteentec.yoko.server.model;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 


public class User extends BaseModel{
	private int id;
	private String mobile;  
    private String username;
    private String password;
    
    public User(){
    	
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getMobile() {  
        return mobile;  
    }  
  
    public void setMobile(String mobile) {  
        this.mobile = mobile;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }     
  
}
