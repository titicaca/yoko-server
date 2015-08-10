package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.AccountService;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/user")  
@EnableAutoConfiguration

public class UserController {
	@Autowired
	AccountService accountService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Get userinfo
	 * @param principal
	 * @return user
	 */
	@RequestMapping(value="/userinfo",method=RequestMethod.GET)
	public User getUser(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) {
			logger.error("[getUser] user: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return accountService.getUser(Account.findMobile(principal.getName()));
	}
	
	/**
	 * update userinfo
	 * @param principal
	 * @param postclass
	 * @return boolean
	 */
	@RequestMapping(value="/userinfo", method=RequestMethod.PUT)
	public ResponseResult updateUser(Principal principal, @RequestBody User postclass){
		if(!Account.findRole(principal.getName()).equals("0")) {
			logger.error("[updateUser] user: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return accountService.updateUserInfo(Account.findMobile(principal.getName()), postclass);
	}
	

	
}
