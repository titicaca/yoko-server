package com.fifteentec.yoko.server.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.ScheduleNotFoundException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Schedule;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.ScheduleRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
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
		Set<Schedule> results = new HashSet<Schedule>();
		for (Schedule s : schedules){
			if(s.getStatus() == 0){
				results.add(s);
			}
		}
		return results;
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
			schedule = scheduleRepository.save(schedule);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		ResponseResult r =  new ResponseResult(true);
		r.setMsg(String.format("{\"postid\":%s｝", schedule.getId()));
		return r;
	}
	
	public ResponseResult updateSchedule(String user_mobile, Schedule postclass){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[updateSchedule] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Schedule schedule = scheduleRepository.findOne(postclass.getId());
		if (schedule == null){
			logger.error("［updateSchedule] schedule id:" + postclass.getId() + "doesn't exist; ");
			throw new ScheduleNotFoundException(postclass.getId());
		}
		if (schedule.getUser().getMobile().equals(user_mobile)){
			logger.error("［updateSchedule] schedule id:" + postclass.getId() + "doesn't belong to the user; ");
			throw new ScheduleNotFoundException(postclass.getId());
		}
		
		if (schedule.getUpdatedtime().after( postclass.getUpdatedtime() ) ){
			ResponseResult r = new ResponseResult(false);
			String json = JsonConverterUtil.convertObjToString(schedule);
			String msg = String.format("{\"updatedSchedule\":%s}", json);
			r.setMsg(msg);
			return r;
		}
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
	
	public ResponseResult delSchedule(String user_mobile, Long schedule_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[delSchedule] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Schedule schedule = scheduleRepository.findOne(schedule_id);
		if (schedule == null){
			logger.error("［delSchedule] schedule id:" + schedule_id + "doesn't exist; ");
			throw new ScheduleNotFoundException(schedule_id);
		}
		if (schedule.getUser().getMobile().equals(user_mobile)){
			logger.error("［delSchedule] schedule id:" +  schedule_id + "doesn't belong to the user; ");
			throw new ScheduleNotFoundException(schedule_id);
		}
		//delete
		schedule.setStatus(1);
		try{
			scheduleRepository.save(schedule);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}

}
