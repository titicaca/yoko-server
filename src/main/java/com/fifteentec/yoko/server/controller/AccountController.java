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
public class AccountController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)  
    public Result addAccount(@RequestBody SignUpInfo postclass) {  
		Account account = new Account();
		account.setUsername("0_"+postclass.getMobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	return new Result(false);
        }
        User user = new User();
        user.setNickname(postclass.getNickname());
        user.setMobile(postclass.getMobile());
        try{
        	userRepository.save(user);
        }
        catch(Exception e){
        	return new Result(false);
        }
        return new Result(true);
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.PUT)  
    public Result updateAccount(@RequestBody SignUpInfo postclass) {  
		Account account = accountRepository.findByUsername("0_"+postclass.getMobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	return new Result(false);
        }
        return new Result(true);
	}
	
}

class SignUpInfo{
	private String nickname;
	private String mobile;
	private String password;
	
	public String getNickname(){
		return nickname;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getPassword() {
		return password;
	}
	
	
}
