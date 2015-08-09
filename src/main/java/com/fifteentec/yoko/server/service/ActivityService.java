package com.fifteentec.yoko.server.service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fifteentec.yoko.server.exception.ActivityNotFoundException;
import com.fifteentec.yoko.server.exception.OrganizationNotFoundException;
import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@Service
public class ActivityService {
	
	private Logger logger = LoggerFactory.getLogger(ActivityService.class);
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Activity getActivity(Long activity_id){
		Activity a = activityRepository.findById(activity_id);
		if (a == null){
			logger.error("[getActivity] activity:" + activity_id + "doesn't exist; "  );
			throw new ActivityNotFoundException(activity_id);
		}else{
			logger.info("[getActivity] get activity:" + activity_id );
			return a;			
		}
	}
	
	public Set<Activity> getUserAllActivities(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getAllActivities] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Set<Organization> organizations = user.getOrganizations();
		Set<Activity> activities = new HashSet<Activity>();
		for (Organization eachorganization : organizations) {
			activities.addAll(eachorganization.getActivities());
		}
		return activities;
	}
	
	public Set<Activity> getActivitiesByOrganization(Long org_id){
		Organization organization = organizationRepository.findById(org_id);
		if (organization == null){
			logger.error("[getActivitiesByOrganization] organization: " + org_id + "doesn't exist.");
			throw new OrganizationNotFoundException(org_id);
		}
		logger.info("[getActivitiesByOrganization] organization: " + org_id + "get activities.");
		return organization.getActivities();
	}
	
	public Set<Activity> getActivitiesByOrganization(String org_mobile){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[getActivitiesByOrganization] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		logger.info("[getActivitiesByOrganization] organization: " + org_mobile + "get activities.");
		return organization.getActivities();
	}
	
	public Boolean addOrganizationActivity(String org_mobile, Activity postclass){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[addActivity] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		try {
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
			activityRepository.save(activity);
		}
		catch(Exception e){
			logger.error("[addActivity] organization: " + org_mobile + 
					"; activity: " + org_mobile + "cannot be saved by ActivityRepository.");
			return false;
		}
		logger.info("[addActivity] organization: " + org_mobile + "post activity " + postclass.getName());
		return true;	
	}
	
	public Set<Activity> getUserEnrollActivities(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserEnrollActivities] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		logger.info("[getUserEnrollActivities] user: " + user_mobile + " get enroll activities");
		return user.getEnrollactivities();
	}
	
	public Set<User> getActivityPaticipators(Long activity_id){
		Activity activity = activityRepository.findById(activity_id);
		return activity.getEnrollusers();
	}
	
	/**
	 * TODO: check the maximum number of participators for the activity
	 * @param user_mobile
	 * @param activity_id
	 * @return
	 */
	public Boolean enrollUserInActivity(String user_mobile ,Long activity_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[enrollUserInActivity] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Activity activity = activityRepository.findById(activity_id);
		if (activity == null){
			logger.error("[enrollUserInActivity] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
		try{
			activity.getEnrollusers().add(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return false;
		}		
		return true;	
	}
	
	/**
	 * delete user from the activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public Boolean delUserFromActivity(String user_mobile ,Long activity_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[delUserFromActivity] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Activity activity = activityRepository.findById(activity_id);	
		if (activity == null){
			logger.error("[delUserFromActivity] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
		try{
			activity.getEnrollusers().remove(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return false;
		}		
		return true;	
	}
	
	/**
	 * get collected activities
	 * @param principal
	 * @return
	 */
	public Set<Activity> getUserCollectedActivities(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserCollectedActivities] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		return user.getCollectactivities();
	}
	
	/**
	 * get collecting user list of an activity 
	 * @param activity_id
	 * @return
	 */
	public Set<User> getActivityCollectingUsers(Long activity_id){
		Activity activity = activityRepository.findById(activity_id);
		if (activity == null){
			logger.error("[getActivityCollectingUsers] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
		return activity.getCollectusers();
	}
	
	/**
	 * Add User collected activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public Boolean addUserCollectedActivity(String user_mobile , Long activity_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[addUserCollectedActivity] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Activity activity = activityRepository.findById(activity_id);	
		if(activity == null){
			logger.error("[addUserCollectedActivity] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
		try{
			activity.getCollectusers().add(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return false;
		}		
		return true;	
	}
	
	/**
	 * delete user conllected activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public Boolean delUserCollectedActivity(String user_mobile , Long activity_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[delUserCollectedActivity] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Activity activity = activityRepository.findById(activity_id);
		if(activity == null){
			logger.error("[delUserCollectedActivity] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
		try{
			activity.getCollectusers().remove(user);
			activityRepository.save(activity);
		}
		catch(Exception e){
			return false;
		}		
		return true;	
	}
	
	
	/**
	 * get user watched organizations
	 * @param principal
	 * @return
	 */
	public Set<Organization> getUserWatchedOrganizations(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getOrganizationsNameByUser] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		return user.getOrganizations();
		
	}
	
	/**
	 * get organization watching users
	 * @param principal
	 * @return
	 */
	public Set<User> getOrganizationWatchingUsers(String org_mobile){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[getOrganizationWatchingUsers] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		return organization.getUsers();
	}
	
	public Boolean addUserWatchedOrganization(String user_mobile, Long organization_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[addUserEnrollOrganization] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Organization organization = organizationRepository.findById(organization_id);	
		if (organization == null){
			logger.error("[addUserEnrollOrganization] organization id: " + organization_id + "doesn't exist.");
			throw new OrganizationNotFoundException(organization_id);
		}
		try{
			organization.getUsers().add(user);
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return false;
		}
		return true;	
	}
	
	public Boolean delUserWatchedOrganization(String user_mobile, Long organization_id){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[delUserEnrollOrganization] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Organization organization = organizationRepository.findById(organization_id);
		if (organization == null){
			logger.error("[delUserEnrollOrganization] organization id: " + organization_id + "doesn't exist.");
			throw new OrganizationNotFoundException(organization_id);
		}
		try{
			organization.getUsers().remove(user);
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return false;
		}
		return true;	
	}
	
}
