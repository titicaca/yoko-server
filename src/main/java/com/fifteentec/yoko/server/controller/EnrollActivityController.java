package com.fifteentec.yoko.server.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/enrollactivity")  
@EnableAutoConfiguration

public class EnrollActivityController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;
	
	@RequestMapping(value="/byuser/{user_id}",method=RequestMethod.GET)
	public Set<Activity> getActivitiesByUser(@PathVariable("user_id") Long user_id){
		User user = userRepository.findById(user_id);
		return user.getEnrollactivities();
	}
	
	@RequestMapping(value="/byactivity/{activity_id}",method=RequestMethod.GET)
	public Set<User> getUserByActivity(@PathVariable("activity_id") Long activity_id){
		Activity activity = activityRepository.findById(activity_id);
		return activity.getEnrollusers();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addUserEnrollActivity(@RequestBody UserAndActivity postclass){
		Activity activity = activityRepository.findById(postclass.getActivity_id());
		User user = userRepository.findById(postclass.getUser_id());	
		try{
			activity.getEnrollusers().add(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return new Result(false);
		}		
		return new Result(true);	
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public Result delUserEnrollActivity(@RequestBody UserAndActivity postclass){
		Activity activity = activityRepository.findById(postclass.getActivity_id());
		User user = userRepository.findById(postclass.getUser_id());	
		try{
			activity.getEnrollusers().remove(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return new Result(false);
		}		
		return new Result(true);	
	}

}

class UserAndActivity{
	Long user_id;
	Long activity_id;
	public Long getUser_id() {
		return user_id;
	}
	public Long getActivity_id() {
		return activity_id;
	}	
}
