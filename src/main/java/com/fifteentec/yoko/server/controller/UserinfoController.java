package com.fifteentec.yoko.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
	
	
	@RequestMapping(value="/{user_id}",method=RequestMethod.GET)
	public Userinfo getUserInfo(@PathVariable("user_id") Long user_id){
		Userinfo userinfo = userinfoRepository.findByUser_id(user_id);
		return userinfo;
	}
	
	@RequestMapping(value="/{user_id}",method=RequestMethod.POST)
	public Result addUserInfo(@PathVariable("user_id") Long user_id, @RequestBody Userinfo postclass){
		User selectuser = userRepository.findById(user_id);
		Userinfo userinfo = new Userinfo();
		userinfo.setSex(postclass.getSex());
		userinfo.setEmail(postclass.getEmail());
		userinfo.setQq(postclass.getQq());
		userinfo.setWechat(postclass.getWechat());
		userinfo.setWeibo(postclass.getWeibo());
		userinfo.setPicturelink(postclass.getPicturelink());	
		userinfo.setUser(selectuser);
		try{
			userinfoRepository.save(userinfo);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}
