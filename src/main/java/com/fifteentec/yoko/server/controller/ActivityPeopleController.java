package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/activitypeople")  
@EnableAutoConfiguration
public class ActivityPeopleController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ActivityPeople addActivityPeople(@RequestBody ActivityPeople postclass){
		ActivityPeople activitypeople = new ActivityPeople();
		activitypeople.setActivity_id(postclass.getActivity_id());
		activitypeople.setPeopleenroll(0);
		return activitypeople;
	}

}
