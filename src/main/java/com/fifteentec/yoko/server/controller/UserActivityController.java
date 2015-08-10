package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.service.ActivityService;
import com.fifteentec.yoko.server.util.ContainerToJsonStringConverter;

@RestController  
@RequestMapping("/user/myactivity")  
@EnableAutoConfiguration
public class UserActivityController {
	
	@Autowired
	ActivityService activityService;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * User action: get all the activities of the user followed activities
	 * @param user_id
	 * @return activities
	 */
	@RequestMapping(value="/activities",method=RequestMethod.GET)
	public String getAllActivities(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getUserAllActivities(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(activities);		
	}
	
	/**
	 * Get the activities of one org
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/{org_id}/activities",method=RequestMethod.GET)
	public String getOrganizationActivities(Principal principal, @PathVariable("org_id") Long org_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getActivitiesByOrganization(org_id);
		return ContainerToJsonStringConverter.convertSetToJsonString(activities);		
	}
	
	
	/**
	 * Get all enroll activities
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/enroll/activities",method=RequestMethod.GET)
	public String getUserEnrollActivities(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getUserEnrollActivities(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(activities);
	}
	
	/**
	 * enroll an activity 
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	@RequestMapping(value="/enroll/{activity_id}",method=RequestMethod.POST)
	public ResponseEntity<String> enrollActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean result = activityService.enrollUserInActivity(Account.findMobile(principal.getName()), activity_id);
		return new Result(result).getResponseResult();
	}
	
	/**
	 * Get participators of one activity
	 * @param activity_id
	 * @return
	 */
	@RequestMapping(value="/{activity_id}/participators",method=RequestMethod.GET)
	public String getUserByActivity(@PathVariable("activity_id") Long activity_id){
		Set<User> users = activityService.getActivityPaticipators(activity_id);
		return ContainerToJsonStringConverter.convertSetToJsonString(users);
	}
	
	
	@RequestMapping(value="{activity_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delUserEnrollActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean result = activityService.delUserFromActivity(Account.findMobile(principal.getName()), activity_id);
		return new Result(result).getResponseResult();
	}
	
	@RequestMapping(value="/collect/activities",method=RequestMethod.GET)
	public String getUserCollectedActivities(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getUserCollectedActivities(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(activities);
	}
	
	@RequestMapping(value="/collect/{activity_id}/users",method=RequestMethod.GET)
	public String getActivityCollectingUsers(Principal principal, @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User> users = activityService.getActivityCollectingUsers(activity_id);
		return ContainerToJsonStringConverter.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/collect/{activity_id}",method=RequestMethod.POST)
	public ResponseEntity<String> addUserCollectActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = activityService.addUserCollectedActivity(Account.findMobile(principal.getName()), activity_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/collect/{activity_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delUserCollectedActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = activityService.delUserCollectedActivity(Account.findMobile(principal.getName()), activity_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/watch/orgs",method=RequestMethod.GET)
	public String getUserWatchedOrganizations(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Organization> orgs = activityService.getUserWatchedOrganizations(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(orgs);
	}
	
	@RequestMapping(value="/watch/{organization_id}",method=RequestMethod.POST)
	public ResponseEntity<String> addUserWatchedOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = activityService.addUserCollectedActivity(Account.findMobile(principal.getName()), organization_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/watch/{organization_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delUserWatchedOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = activityService.delUserWatchedOrganization(Account.findMobile(principal.getName()), organization_id);
		return new Result(r).getResponseResult();
	}
	
	
	
}