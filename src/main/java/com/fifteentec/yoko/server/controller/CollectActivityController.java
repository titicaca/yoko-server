package com.fifteentec.yoko.server.controller;

import java.awt.List;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/collectactivity")  
@EnableAutoConfiguration
public class CollectActivityController {          
	@Autowired
	UserRepository userRepository;
	@Autowired
	ActivityRepository activityRepository;
	
	@RequestMapping(value="/byuser",method=RequestMethod.GET)
	public Set<Activity> getActivitiesByUser(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		return user.getCollectactivities();
	}
	
	@RequestMapping(value="/byactivity/{activity_id}",method=RequestMethod.GET)
	public Set<User> getUserByActivity(@PathVariable("activity_id") Long activity_id){
		Activity activity = activityRepository.findById(activity_id);
		return activity.getCollectusers();
	}
	
	@RequestMapping(value="{activity_id}",method=RequestMethod.POST)
	public Result addUserCollectActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Activity activity = activityRepository.findById(activity_id);	
		try{
			activity.getCollectusers().add(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return new Result(false);
		}		
		return new Result(true);	
	}
	
	@RequestMapping(value="{activity_id}",method=RequestMethod.DELETE)
	public Result delUserCollectActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Activity activity = activityRepository.findById(activity_id);
		try{
			activity.getCollectusers().remove(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return new Result(false);
		}		
		return new Result(true);	
	}

}

