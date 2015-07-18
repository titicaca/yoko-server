package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.Activity;


@RestController  
@RequestMapping("/activity")  
@EnableAutoConfiguration
public class ActivityController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Activity addActivity(@RequestBody Activity postclass){
		Activity activity = new Activity();
		activity.setName(postclass.getName());
		return activity;
	}

}
