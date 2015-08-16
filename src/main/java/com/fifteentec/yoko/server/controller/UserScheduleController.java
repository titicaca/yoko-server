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
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/user/myschedule")  
@EnableAutoConfiguration

public class UserScheduleController {
	
	@Autowired
	ScheduleService scheduleService;

	
	@RequestMapping(value="/schedules", method=RequestMethod.GET)
	public String getSchedules(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Schedule> schedules = scheduleService.getSchedules(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(schedules);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseResult addSchedule(Principal principal, @RequestBody Schedule postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return scheduleService.addSchedule(Account.findMobile(principal.getName()), postclass);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseResult updateSchedule(Principal principal, @RequestBody Schedule postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return scheduleService.updateSchedule(Account.findMobile(principal.getName()), postclass);
	}
	
	@RequestMapping(value="/{schedule_id}", method=RequestMethod.DELETE)
	public ResponseResult delSchedule(Principal principal,  @PathVariable("schedule_id") Long schedule_id ){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return scheduleService.delSchedule(Account.findMobile(principal.getName()), schedule_id);
	}

}
