package com.fifteentec.yoko.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

@Entity
@Table(name = "Users")
public class User extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Version
	private Long version;
	@Column(name="mobile")
	private String mobile;  
	@Column(name="username")
    private String username;
	@Column(name="password")
    private String password;
	@OneToOne(cascade=CascadeType.ALL)
	private UserInfo userinfo;
	
//	private UserInfo userinfo;
    
    public User(){
    	
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
