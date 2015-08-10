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
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;


@RestController  
@EnableAutoConfiguration
public class AccountController {
	
	@Autowired
	AccountService userAccountService;
	
	@RequestMapping(value="/signup/user",method=RequestMethod.POST)  
    public ResponseResult addUserAccount(@RequestBody SignUpInfo postclass) {  
		return userAccountService.addUserAccount(postclass);
	}
	
	@RequestMapping(value="/signup/user",method=RequestMethod.PUT)  
    public ResponseResult updateUserAccount(@RequestBody SignUpInfo postclass) { 
		return userAccountService.updateUserAccount(postclass);
	}
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.POST)  
    public ResponseResult addSponsorAccount(@RequestBody SignUpInfo postclass) {  
		return userAccountService.addSponsorAccount(postclass);
	}
	
	@RequestMapping(value="/signup/organization",method=RequestMethod.PUT)  
    public ResponseResult updateSponsorAccount(@RequestBody SignUpInfo postclass) {  
		return userAccountService.updateSponsorAccount(postclass);
	}
	
}


