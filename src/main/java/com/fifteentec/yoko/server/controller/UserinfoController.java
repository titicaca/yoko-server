package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.repository.UserinfoRepository;

@RestController  
@RequestMapping("/userinfo")  
@EnableAutoConfiguration

public class UserinfoController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserinfoRepository userinfoRepository;
	
	
	@RequestMapping(method=RequestMethod.GET)
	@Secured("ROLE_USER")
	public Userinfo getUserInfo(Principal principal){
		User user =userRepository.findByMobile(principal.getName());
		Userinfo userinfo = userinfoRepository.findByUser_id(user.getId());
		return userinfo;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Secured("ROLE_USER")
	public Result addUserInfo(Principal principal, @RequestBody Userinfo postclass){
		User user = userRepository.findByMobile(principal.getName());
		Userinfo userinfo = new Userinfo();
		userinfo.setUsername(postclass.getUsername());
		userinfo.setSex(postclass.getSex());
		userinfo.setEmail(postclass.getEmail());
		userinfo.setQq(postclass.getQq());
		userinfo.setWechat(postclass.getWechat());
		userinfo.setWeibo(postclass.getWeibo());
		userinfo.setPicturelink(postclass.getPicturelink());	
		userinfo.setUser(user);
		try{
			userinfoRepository.save(userinfo);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@Secured("ROLE_USER")
	public Result updateUserInfo(Principal principal, @RequestBody Userinfo postclass){
		User user = userRepository.findByMobile(principal.getName());
		Userinfo userinfo = new Userinfo();
		userinfo.setUsername(postclass.getUsername());
		userinfo.setSex(postclass.getSex());
		userinfo.setEmail(postclass.getEmail());
		userinfo.setQq(postclass.getQq());
		userinfo.setWechat(postclass.getWechat());
		userinfo.setWeibo(postclass.getWeibo());
		userinfo.setPicturelink(postclass.getPicturelink());	
		userinfo.setUser(user);
		try{
			userinfoRepository.save(userinfo);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}
