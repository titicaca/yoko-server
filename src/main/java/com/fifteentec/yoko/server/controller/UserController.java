package com.fifteentec.yoko.server.controller;


import org.springframework.beans.factory.annotation.Autowired;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.*;


@RestController  
@RequestMapping("/user")  
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)  
    public User getUser(@PathVariable("id") Long id) {  
		User user = userRepository.findById(id);
		return user;
    } 
	
	@RequestMapping(method=RequestMethod.POST)  
    public Result addUser(@RequestBody User postclass) {  
		User user = new User();    
        user.setUsername(postclass.getUsername()); 
		user.setMobile(postclass.getMobile());
        user.setPassword(postclass.getPassword());
        try{
        	userRepository.save(user);
        }
        catch(Exception e){
        	return new Result(false);
        }
        
        return new Result(true);
	}
	
	@RequestMapping(method=RequestMethod.PUT)  
    public Result updateUser(@RequestBody User putclass) {  
		User user = userRepository.findById(putclass.getId());  
		user.setUsername(putclass.getUsername()); 
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
