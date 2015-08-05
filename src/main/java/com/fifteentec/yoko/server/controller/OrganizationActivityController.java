package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.service.ActivityService;
import com.fifteentec.yoko.server.util.ContainerToJsonStringConverter;

@RestController  
@RequestMapping("/organization/myactivity")  
@EnableAutoConfiguration
public class OrganizationActivityController {
	
	private Logger logger = LoggerFactory.getLogger(OrganizationActivityController.class);

	
	@Autowired
	ActivityService activityService;
	
	
	@RequestMapping(value="/{activity_id}",method=RequestMethod.GET)
	public Activity getActivity(@PathVariable("activity_id") Long activity_id){
		return activityService.getActivity(activity_id);
	}
	
	
	/**
	 * Org action: get activities
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="host/activities",method=RequestMethod.GET)
	public String getActivitiesByOrganization(Principal principal){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[getActivitiesByOrganization] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		Set<Activity> activities = activityService.getActivitiesByOrganization(Account.findMobile(principal.getName()));
		return ContainerToJsonStringConverter.convertSetToJsonString(activities);		
	}
	

	
	/**
	 * Org acction: add activity
	 * @param principal
	 * @param postclass
	 * @return	boolean
	 */
	@RequestMapping(value="/host", method=RequestMethod.POST)
	public ResponseEntity<Boolean> addActivity(Principal principal, @RequestBody Activity postclass){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[addActivity] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		Boolean result = activityService.addOrganizationActivity(Account.findMobile(principal.getName()), postclass);
		return new Result(result).getResponseResult();
	}
}
