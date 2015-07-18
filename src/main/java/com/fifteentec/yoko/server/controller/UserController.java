package com.fifteentec.yoko.server.controller;


/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/user")  
@EnableAutoConfiguration
public class UserController {
	@RequestMapping(value="/{id}",method=RequestMethod.GET)  
    public User getUser(@PathVariable("id") Long id) {  
		User user = new User();  	
	    user.setMobile("135");  
	    user.setUsername("zhang");   
	    
		return user;  
    } 
	@RequestMapping(value="/{id}",method=RequestMethod.POST)  
    public User postUser(@PathVariable("id") Long id,@RequestBody User myuser) {  
		User user = new User();  
		user.setMobile(myuser.getMobile());  
        user.setUsername(myuser.getUsername()); 
        return user;
	}
	
	@RequestMapping(method=RequestMethod.POST)  
    public User addUser(@RequestBody User postclass) {  
		User user = new User();  
		user.setMobile(postclass.getMobile());  
        user.setUsername(postclass.getUsername()); 
        user.setPassword(postclass.getPassword());
        return user;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)  
    public User putUser(@PathVariable("id") Long id,@RequestBody User myuser) {  
		User user = new User();  
		user.setMobile(myuser.getMobile());  
        user.setUsername(myuser.getUsername()); 
         
        return user;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)  
    public User deleteUser(@PathVariable("id") Long id) {  
		User user = new User();  
		user.setMobile("13512147293");  
        user.setUsername("li"); 
        return user;
	}

}
