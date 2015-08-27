package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.service.ActivityService;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

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
		return JsonConverterUtil.convertSetToJsonString(activities);		
	}
	
	@RequestMapping(value="/activities/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getAllActivitiesWithPaging(Principal principal,@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Activity> activities = activityService.getUserAllActivitiesWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(activities);		
	}
	
	/**d
	 * Get the activities of one org
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/{org_id}/activities",method=RequestMethod.GET)
	public String getOrganizationActivities(Principal principal, @PathVariable("org_id") Long org_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getActivitiesByOrganization(org_id);
		return JsonConverterUtil.convertSetToJsonString(activities);		
	}
	
	
	/**
	 * Get all enroll activities with paging
	 * TODO test if it works right
	 * @param principal
	 * @return
	 */
	@RequestMapping(value="/enroll/activities/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserEnrollActivitiesWithPaging(Principal principal, @PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Activity> activities = activityService.getUserEnrollActivitiesWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(activities);
	}
	
	
	@RequestMapping(value="/enroll/activities",method=RequestMethod.GET)
	public String getUserEnrollActivities(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getUserEnrollActivities(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(activities);
	}
	
	/**
	 * enroll an activity 
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	@RequestMapping(value="/enroll/{activity_id}",method=RequestMethod.POST)
	public ResponseResult enrollActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.enrollUserInActivity(Account.findMobile(principal.getName()), activity_id);
	}
	
	/**
	 * Get participators of one activity
	 * @param activity_id
	 * @return
	 */
	@RequestMapping(value="/{activity_id}/participators",method=RequestMethod.GET)
	public String getUserByActivity(@PathVariable("activity_id") Long activity_id){
		Set<User> users = activityService.getActivityPaticipators(activity_id);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	//TODO check if it works right
	@RequestMapping(value="/{activity_id}/participators/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserByActivityWithPaging(@PathVariable("activity_id") Long activity_id,
			@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		List<User> users = activityService.getActivityPaticipatorsWithPaging(activity_id, pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	
	@RequestMapping(value="/enroll/{activity_id}",method=RequestMethod.DELETE)
	public ResponseResult delUserEnrollActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.delUserFromActivity(Account.findMobile(principal.getName()), activity_id);
	}
	
	@RequestMapping(value="/collect/activities",method=RequestMethod.GET)
	public String getUserCollectedActivities(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Activity> activities = activityService.getUserCollectedActivities(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(activities);
	}
	
	//TODO to be tested
	@RequestMapping(value="/collect/activities/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserCollectedActivities(Principal principal, @PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Activity> activities = activityService.getUserCollectedActivitiesWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(activities);
	}
	
	@RequestMapping(value="/collect/{activity_id}/users",method=RequestMethod.GET)
	public String getActivityCollectingUsers(Principal principal, @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User> users = activityService.getActivityCollectingUsers(activity_id);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	//TODO to be tested
	@RequestMapping(value="/collect/{activity_id}/users/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getActivityCollectingUsers(Principal principal, @PathVariable("activity_id") Long activity_id,
			@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<User> users = activityService.getActivityCollectingUsersWithPaging(activity_id, pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(users);
	}
	
	@RequestMapping(value="/collect/{activity_id}",method=RequestMethod.POST)
	public ResponseResult addUserCollectActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.addUserCollectedActivity(Account.findMobile(principal.getName()), activity_id);
	}
	
	@RequestMapping(value="/collect/{activity_id}",method=RequestMethod.DELETE)
	public ResponseResult delUserCollectedActivity(Principal principal , @PathVariable("activity_id") Long activity_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.delUserCollectedActivity(Account.findMobile(principal.getName()), activity_id);
	}
	
	@RequestMapping(value="/watch/orgs",method=RequestMethod.GET)
	public String getUserWatchedOrganizations(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Organization> orgs = activityService.getUserWatchedOrganizations(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(orgs);
	}
	
	@RequestMapping(value="/watch/orgs/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String getUserWatchedOrganizationsWithPaging(Principal principal, @PathVariable int pageno, @PathVariable int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Organization> orgs = activityService.getUserWatchedOrganizationsWithPaging(Account.findMobile(principal.getName()), pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(orgs);
	}
	
	@RequestMapping(value="/watch/{organization_id}",method=RequestMethod.POST)
	public ResponseResult addUserWatchedOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.addUserWatchedOrganization(Account.findMobile(principal.getName()), organization_id);
	}
	
	@RequestMapping(value="/watch/{organization_id}",method=RequestMethod.DELETE)
	public ResponseResult delUserWatchedOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return activityService.delUserWatchedOrganization(Account.findMobile(principal.getName()), organization_id);
	}
	
	@RequestMapping(value="/search/{organization_name}/page/{pageno}/{pagesize}",method=RequestMethod.GET)
	public String searchOrganizationWithPaging(Principal principal ,@PathVariable("organization_name") String organization_name,
			@PathVariable("pageno") int pageno, @PathVariable("pagesize") int pagesize){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		List<Organization> orgs = activityService.searchOrganizationWithPaging(organization_name, pageno, pagesize);
		return JsonConverterUtil.convertSetToJsonString(orgs);
	}
	
	
	
	
}
