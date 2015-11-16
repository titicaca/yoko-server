package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.service.ActivityService;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

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
	

	@RequestMapping(value="/{activity_id}/participators",method=RequestMethod.GET)
	public String getUsersByActivity(Principal principal, @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		Set<User> users = activityService.getActivityPaticipators(activity_id);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/{activity_id}/participators/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUsersByActivityWithPaging(@PathVariable("activity_id") Long activity_id, 
			@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		List<User> users = activityService.getActivityPaticipatorsWithPaging(activity_id, pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	

	@RequestMapping(value="/host/activities",method=RequestMethod.GET)
	public String getActivitiesByOrganization(Principal principal){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[getActivitiesByOrganization] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		Set<Activity> activities = activityService.getActivitiesByOrganization(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(activities);		
	}
	
	@RequestMapping(value="/host/activities/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getActivitiesByOrganizationWithPaging(Principal principal, @PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[getActivitiesByOrganization] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		List<Activity> activities = activityService.getActivitiesByOrganizationWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(activities);		
	}
	

	@RequestMapping(value="/host", method=RequestMethod.POST)
	public ResponseResult addActivity(Principal principal, @RequestBody Activity postclass){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[addActivity] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return activityService.addOrganizationActivity(Account.findMobile(principal.getName()), postclass);
	}
	
	@RequestMapping(value="/host/{activity_id}", method=RequestMethod.DELETE)
	public ResponseResult deleteActivity(Principal principal, @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[deleteActivity] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return activityService.delOrganizationActivity(Account.findMobile(principal.getName()), activity_id);
	}
	

	@RequestMapping(value="/collect/{activity_id}/users",method=RequestMethod.GET)
	public String getActivityCollectingUsers(Principal principal, @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User> users = activityService.getActivityCollectingUsers(activity_id);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/collect/{activity_id}/users/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getActivityCollectingUsers(Principal principal, @PathVariable("activity_id") Long activity_id,
			@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<User> users = activityService.getActivityCollectingUsersWithPaging(activity_id, pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/watched/users",method=RequestMethod.GET)
	public String getOrganizationWatchingUsers(Principal principal){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		Set<User> users = activityService.getOrganizationWatchingUsers(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/watched/users/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getOrganizationWatchingUsers(Principal principal, @PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		List<User> users = activityService.getOrganizationWatchingUsersWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
}
