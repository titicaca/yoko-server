package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.ScheduleService;
import com.fifteentec.yoko.server.util.ContainerToJsonStringConverter;

@RestController  
@RequestMapping("/schedule")  
@EnableAutoConfiguration

public class UserScheduleController {
	
	@Autowired
	ScheduleService scheduleService;

	
	@RequestMapping(method=RequestMethod.GET)
	public String getSchedules(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Schedule> schedules = scheduleService.getSchedules(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(schedules);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addSchedule(Principal principal, @RequestBody Schedule postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = scheduleService.addSchedule(Account.findMobile(principal.getName()), postclass);
		return new Result(r).getResponseResult();
	}

}