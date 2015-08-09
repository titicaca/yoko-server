package com.fifteentec.yoko.server.controller;



import org.springframework.beans.factory.annotation.Autowired;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

import org.springframework.boot.autoconfigure.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.AccountService;


@RestController  
@EnableAutoConfiguration
public class AccountController {
	
	@Autowired
	AccountService userAccountService;
	
	@RequestMapping(value="/signup/user",method=RequestMethod.POST)  
    public ResponseEntity<String> addUserAccount(@RequestBody SignUpInfo postclass) {  
		return new Result(userAccountService.addUserAccount(postclass)).getResponseResult();
	}
	
	@RequestMapping(value="/signup/user",method=RequestMethod.PUT)  
    public ResponseEntity<String> updateUserAccount(@RequestBody SignUpInfo postclass) { 
		return new Result(userAccountService.updateUserAccount(postclass)).getResponseResult();
	}
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.POST)  
    public ResponseEntity<String> addSponsorAccount(@RequestBody SignUpInfo postclass) {  
		return new Result(userAccountService.addSponsorAccount(postclass)).getResponseResult();
	}
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.PUT)  
    public ResponseEntity<String> updateSponsorAccount(@RequestBody SignUpInfo postclass) {  
		return new Result(userAccountService.updateSponsorAccount(postclass)).getResponseResult();
	}
	
}


