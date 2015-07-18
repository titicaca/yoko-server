package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.UserMoreInfo;

@RestController  
@RequestMapping("/usermoreinfo")  
@EnableAutoConfiguration
public class UserMoreInfoController {
	@RequestMapping(method=RequestMethod.POST)
	public UserMoreInfo addUserMoreInfo(@RequestBody UserMoreInfo postclass){
		UserMoreInfo usermoreinfo = new UserMoreInfo();
		usermoreinfo.setSex(postclass.getSex());
		usermoreinfo.setEmail(postclass.getEmail());
		usermoreinfo.setQq(postclass.getQq());
		usermoreinfo.setWechat(postclass.getWechat());
		return usermoreinfo;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public UserMoreInfo updateUserMoreInfo(@RequestBody UserMoreInfo putclass){
		UserMoreInfo usermoreinfo = new UserMoreInfo();
		usermoreinfo.setSex(putclass.getSex());
		usermoreinfo.setEmail(putclass.getEmail());
		usermoreinfo.setQq(putclass.getQq());
		usermoreinfo.setWechat(putclass.getWechat());
		return usermoreinfo;
	}
	

}
