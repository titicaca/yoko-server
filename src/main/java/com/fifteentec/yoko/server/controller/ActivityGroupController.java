package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fifteentec.yoko.server.model.ActivityGroup;

@RestController  
@RequestMapping("/activitygroup")  
@EnableAutoConfiguration
public class ActivityGroupController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ActivityGroup addActivityGroup(@RequestBody ActivityGroup postclass){
		ActivityGroup activitygroup = new ActivityGroup();
		activitygroup.setName(postclass.getName());
		activitygroup.setSponsor_id(postclass.getSponsor_id());
		activitygroup.setIntroduction(postclass.getIntroduction());
		
		return activitygroup;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ActivityGroup updateActivityGroup(@RequestBody ActivityGroup putclass){
		ActivityGroup activitygroup = new ActivityGroup();
		activitygroup.setName(putclass.getName());
		activitygroup.setSponsor_id(putclass.getSponsor_id());
		activitygroup.setIntroduction(putclass.getIntroduction());
		
		return activitygroup;
	}

}
