package com.fifteentec.yoko.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.Activity;

import com.fifteentec.yoko.server.service.ActivityService;

@ComponentScan
@RestController  
@RequestMapping("/public/activity")  
@EnableAutoConfiguration
public class ActivityController {
	
	@Autowired
	ActivityService activityService;
	
	@RequestMapping(value="/{activity_id}")
	public Activity getActivity(@PathVariable("activity_id") Long activity_id){
		return activityService.getActivity(activity_id);
	}

}
