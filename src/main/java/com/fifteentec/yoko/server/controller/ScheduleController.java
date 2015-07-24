package com.fifteentec.yoko.server.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.ScheduleRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/schedule")  
@EnableAutoConfiguration

public class ScheduleController {
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/{user_id}",method=RequestMethod.GET)
	public Set<Schedule> getSchedules(@PathVariable("user_id") Long user_id){
		Set<Schedule> schedules = scheduleRepository.findByUser_id(user_id);
		return schedules;
	}
	
	@RequestMapping(value="/{user_id}",method=RequestMethod.POST)
	public Result addSchedule(@PathVariable("user_id") Long user_id, @RequestBody Schedule postclass){
		Schedule schedule = new Schedule();
		schedule.setName(postclass.getName());
		schedule.setTimebegin(postclass.getTimebegin());
		schedule.setTimeend(postclass.getTimeend());
		schedule.setLocation(postclass.getLocation());
		schedule.setType(postclass.getType());
		schedule.setRemind(postclass.getRemind());
		schedule.setDuplication(postclass.getDuplication());
		schedule.setIntroduction(postclass.getIntroduction());
		schedule.setProperty(postclass.getProperty());
		schedule.setDetaillink(postclass.getDetaillink());
		schedule.setUser(userRepository.findById(user_id));
		
		try{
			scheduleRepository.save(schedule);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}
