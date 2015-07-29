package com.fifteentec.yoko.server.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

import org.springframework.boot.autoconfigure.*;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.*;

import com.fifteentec.yoko.server.exception.UserNotFoundException;

@RestController  
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserinfoRepository userinfoRepository;
	
	@RequestMapping(value="/user",method=RequestMethod.GET)  
	@Secured("ROLE_USER")
    public User getUser(Principal principal) { 
		String userMobile=principal.getName();
		try{
			User user = userRepository.findByMobile(userMobile);
			return user;
		}catch(Exception e){
			throw new UserNotFoundException(userMobile.toString());
		}
    } 
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)  
    public Result addUser(@RequestBody SignUp postclass) {  
		User user = new User();    
		user.setMobile(postclass.getMobile());
        user.setPassword(postclass.getPassword());
        Userinfo userinfo = new Userinfo();
		userinfo.setUsername(postclass.getUsername());
		userinfo.setUser(user);
        try{
        	userinfoRepository.save(userinfo);
        }
        catch(Exception e){
        	return new Result(false);
        }
        
        return new Result(true);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.PUT)  
	@Secured("ROLE_USER")
    public Result updateUser(@RequestBody User putclass,Principal principal){
		User user = userRepository.findByMobile(principal.getName());
		user.setMobile(putclass.getMobile());
        user.setPassword(putclass.getPassword());
        try{
        	userRepository.save(user);
        }
        catch(Exception e){
        	return new Result(false);
        }      
        return new Result(true);
	}
	
}

class SignUp{
	private String username;
	private String mobile;
	private String password;
	public String getUsername() {
		return username;
	}
	public String getMobile() {
		return mobile;
	}
	public String getPassword() {
		return password;
	}
	
	
}
