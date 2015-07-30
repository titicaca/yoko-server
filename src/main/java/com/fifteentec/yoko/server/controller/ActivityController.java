package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;


@RestController  
@RequestMapping("/activity")  
@EnableAutoConfiguration
public class ActivityController {
	
	@Autowired
	OrganizationRepository organizationRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/{activity_id}",method=RequestMethod.GET)
	public Activity getActivity(@PathVariable("activity_id") Long activity_id){
		return activityRepository.findById(activity_id);
	}
	
	@RequestMapping(value="/byorganization/{organization_id}",method=RequestMethod.GET)
	public Set<Activity> getActivitiesByOrganization(@PathVariable("organization_id") Long organization_id){
		Organization organization = organizationRepository.findById(organization_id);
		return organization.getActivities();
	}
	
	@RequestMapping(value="/all/{user_id}}",method=RequestMethod.GET)
	public Set<Activity> getAllActivities(@PathVariable("user_id") Long user_id){
		User user = userRepository.findById(user_id);
		Set<Organization> organizations = user.getOrganizations();
		Set<Activity> activities = new HashSet<Activity>();
		for (Organization eachorganization : organizations) {
			activities.addAll(eachorganization.getActivities());
		}
		return activities;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addActivity(Principal principal, @RequestBody Activity postclass){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		Organization organization = organizationRepository.findByMobile(Account.findMobile(principal.getName()));
		Activity activity = new Activity();
		activity.setName(postclass.getName());
		activity.setTimebegin(postclass.getTimebegin());
		activity.setTimeend(postclass.getTimeend());
		activity.setLocation(postclass.getLocation());
		activity.setIntroduction(postclass.getIntroduction());
		activity.setPeopleall(postclass.getPeopleall());
		activity.setPicturelink(postclass.getPicturelink());
		activity.setDetaillink(postclass.getDetaillink());
		activity.setOrganization(organization);
		
		try{
			activityRepository.save(activity);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}

}
