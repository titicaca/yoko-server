package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/userinfo")  
@EnableAutoConfiguration

public class UserInfoController {
	
	@RequestMapping(method=RequestMethod.POST)
	public UserInfo addUserInfo(@RequestBody UserInfo postclass){
		UserInfo userinfo = new UserInfo();
		userinfo.setUser(postclass.getUser());
		userinfo.setSex(postclass.getSex());
		userinfo.setEmail(postclass.getEmail());
		userinfo.setQq(postclass.getQq());
		userinfo.setWechat(postclass.getWechat());
		userinfo.setWeibo(postclass.getWeibo());
		userinfo.setPicturelink(postclass.getPicturelink());
		return userinfo;
	}

}
