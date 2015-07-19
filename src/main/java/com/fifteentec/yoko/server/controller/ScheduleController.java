package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/schedule")  
@EnableAutoConfiguration

public class ScheduleController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Schedule addSchedule(@RequestBody Schedule postclass){
		Schedule schedule = new Schedule();
		schedule.setUser_id(postclass.getUser_id());
		schedule.setTimebegin(postclass.getTimebegin());
		schedule.setTimeend(postclass.getTimeend());
		schedule.setLocation(postclass.getLocation());
		schedule.setType(postclass.getType());
		schedule.setRemind(postclass.getRemind());
		schedule.setDuplication(postclass.getDuplication());
		schedule.setIntroduction(postclass.getIntroduction());
		schedule.setProperty(postclass.getProperty());
		schedule.setDetaillink(postclass.getDetaillink());
		return schedule;
	}

}
