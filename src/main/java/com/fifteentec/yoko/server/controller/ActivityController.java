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
		activity.setActivitygroup_id(postclass.getActivitygroup_id());
		activity.setTimebegin(postclass.getTimebegin());
		activity.setTimeend(postclass.getTimeend());
		activity.setLocation(postclass.getLocation());
		activity.setIntroduction(postclass.getIntroduction());
		activity.setPeopleall(postclass.getPeopleall());
		activity.setPicturelink(postclass.getPicturelink());
		activity.setDetaillink(postclass.getDetaillink());
		return activity;
	}

}
