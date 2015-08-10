package com.fifteentec.yoko.server.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Schedule;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.ScheduleRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class ScheduleService {
	
	private Logger logger = LoggerFactory.getLogger(ScheduleService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	public Set<Schedule> getSchedules(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[getSchedules] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Set<Schedule> schedules = scheduleRepository.findByUser_id(user.getId());
		return schedules;
	}
	
	public ResponseResult addSchedule(String user_mobile, Schedule postclass){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addSchedule] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
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
		schedule.setUser(user);
		
		try{
			scheduleRepository.save(schedule);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}

}
