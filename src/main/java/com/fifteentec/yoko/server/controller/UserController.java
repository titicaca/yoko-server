package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/user")  
@EnableAutoConfiguration

public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public User getUser(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		return user;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Result updateUser(Principal principal, @RequestBody User postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		user.setNickname(postclass.getNickname());
		user.setMobile(postclass.getMobile());
		user.setSex(postclass.getSex());
		user.setEmail(postclass.getEmail());
		user.setQq(postclass.getQq());
		user.setWechat(postclass.getWechat());
		user.setWeibo(postclass.getWeibo());
		user.setPicturelink(postclass.getPicturelink());	
		try{
			userRepository.save(user);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
}
