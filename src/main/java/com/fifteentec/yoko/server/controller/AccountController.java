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
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@RequestMapping(value="/signup/user",method=RequestMethod.POST)  
    public Result addUserAccount(@RequestBody SignUpInfo postclass) {  
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
        user.setNickname(postclass.getName());
        user.setMobile(postclass.getMobile());
        try{
        	userRepository.save(user);
        }
        catch(Exception e){
        	return new Result(false);
        }
        return new Result(true);
	}
	
	@RequestMapping(value="/signup/user",method=RequestMethod.PUT)  
    public Result updateUserAccount(@RequestBody SignUpInfo postclass) {  
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
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.POST)  
    public Result addSponsorAccount(@RequestBody SignUpInfo postclass) {  
		Account account = new Account();
		account.setUsername("1_"+postclass.getMobile());
		account.setPassword(postclass.getPassword());
        try{
        	accountRepository.save(account);
        }
        catch(Exception e){
        	return new Result(false);
        }
        Organization organization = new Organization();
        organization.setRealname(postclass.getName());
        organization.setMobile(postclass.getMobile());
        try{
        	organizationRepository.save(organization);
        }
        catch(Exception e){
        	return new Result(false);
        }
        return new Result(true);
	}
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.PUT)  
    public Result updateSponsorAccount(@RequestBody SignUpInfo postclass) {  
		Account account = accountRepository.findByUsername("1_"+postclass.getMobile());
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
	private String name;
	private String mobile;
	private String password;
	
	public String getName(){
		return name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getPassword() {
		return password;
	}
	
	
}
