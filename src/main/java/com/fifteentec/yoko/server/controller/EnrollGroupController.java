package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.EnrollGroup;

@RestController  
@RequestMapping("/enrollgroup")  
@EnableAutoConfiguration
public class EnrollGroupController {
	
	@RequestMapping(method=RequestMethod.POST)
	public EnrollGroup addEnrollGroup(@RequestBody EnrollGroup postclass){
		EnrollGroup enrollgroup = new EnrollGroup();
		enrollgroup.setUser_id(postclass.getUser_id());
		enrollgroup.setActivitygroup_id(postclass.getActivitygroup_id());
		return enrollgroup;
	}
	

}
