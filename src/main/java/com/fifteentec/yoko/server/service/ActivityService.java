package com.fifteentec.yoko.server.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.ActivityNotFoundException;
import com.fifteentec.yoko.server.exception.OrganizationNotFoundException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class ActivityService {
	
	private Logger logger = LoggerFactory.getLogger(ActivityService.class);
	
	@Autowired
	ActivityRepository activityRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Organization> searchOrganizationWithPaging(String likename, int pageno, int pagesize){
		Pageable pageable = new PageRequest(pageno, pagesize);
		Page<Organization> page = organizationRepository.findByNameLike(likename, pageable);
		return page.getContent();
	}
	
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
	
	public List<Activity> getLatestActivitiesWithPaging(int pageno, int pagesize){
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		Page<Activity> page = activityRepository.findAll(pageable);
		return page.getContent();
	}
	
	
	public Set<Activity> getUserAllActivities(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserAllActivities] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Set<Organization> organizations = user.getOrganizations();
		Set<Activity> activities = new HashSet<Activity>();
		for (Organization eachorganization : organizations) {
			activities.addAll(eachorganization.getActivities());
		}
		return activities;
	}
	
	public List<Activity> getUserAllActivitiesWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserAllActivitiesWithPaging] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Set<Organization> organizations = user.getOrganizations();
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		Page<Activity> page = activityRepository.findByOrganizationIn(organizations, pageable);
		return page.getContent();
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
	
	public List<Activity> getActivitiesByOrganizationWithPaging(Long org_id, int pageno, int pagesize){
		Organization organization = organizationRepository.findById(org_id);
		if (organization == null){
			logger.error("[getActivitiesByOrganizationWithPaging] organization: " + org_id + "doesn't exist.");
			throw new OrganizationNotFoundException(org_id);
		}
		logger.info("[getActivitiesByOrganizationWithPaging] organization: " + org_id + "get activities.");
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		return activityRepository.findByOrganization(pageable).getContent();
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
	
	public List<Activity> getActivitiesByOrganizationWithPaging(String org_mobile, int pageno, int pagesize){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[getActivitiesByOrganizationWithPaging] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		logger.info("[getActivitiesByOrganizationWithPaging] organization: " + org_mobile + "get activities.");
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		return activityRepository.findByOrganization(pageable).getContent();
	}
	
	public ResponseResult addOrganizationActivity(String org_mobile, Activity postclass){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[addActivity] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
		Activity activity = new Activity();
		try {
			activity.setName(postclass.getName());
			activity.setTimebegin(postclass.getTimebegin());
			activity.setTimeend(postclass.getTimeend());
			activity.setLocation(postclass.getLocation());
			activity.setIntroduction(postclass.getIntroduction());
			activity.setPeopleall(postclass.getPeopleall());
			activity.setPicturelink(postclass.getPicturelink());
			activity.setDetaillink(postclass.getDetaillink());
			activity.setOrganization(organization);	
			activity = activityRepository.save(activity);
		}
		catch(Exception e){
			logger.error("[addActivity] organization: " + org_mobile + 
					"; activity: " + org_mobile + "cannot be saved by ActivityRepository.");
			return new ResponseResult(false, e.toString());
		}
		logger.info("[addActivity] organization: " + org_mobile + "post activity " + postclass.getName());
		String msg = String.format("{postid : %s｝", activity.getId());
		return new ResponseResult(true, msg);	
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
	
//	TODO check if it works right
	public List<Activity> getUserEnrollActivitiesWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserEnrollActivitiesWithPaging] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		logger.info("[getUserEnrollActivitiesWithPaging] user: " + user_mobile + " get enroll activities");
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		return activityRepository.findByEnrollingUser(user, pageable).getContent();
	}
	
	
	public Set<User> getActivityPaticipators(Long activity_id){
		Activity activity = activityRepository.findById(activity_id);
		if (activity == null){
			logger.error("[getActivityPaticipators] activity:" + activity_id + "doesn't exist; "  );
			throw new ActivityNotFoundException(activity_id);
		}
		return activity.getEnrollusers();
	}

//  TODO
	public List<User> getActivityPaticipatorsWithPaging(Long activity_id, int pageno, int pagesize){
		Activity activity = activityRepository.findById(activity_id);
		if (activity == null){
			logger.error("[getActivityPaticipatorsWithPaging] activity:" + activity_id + "doesn't exist; "  );
			throw new ActivityNotFoundException(activity_id);
		}
		//TODO how to sort the participators
//		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize);
		return userRepository.findByEnrollActivity(activity, pageable).getContent();
	}
	
	/**
	 * TODO: check the maximum number of participators for the activity
	 * @param user_mobile
	 * @param activity_id
	 * @return
	 */
	public ResponseResult enrollUserInActivity(String user_mobile ,Long activity_id){
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
			return new ResponseResult(false, e.toString());
		}		
		return new ResponseResult(true);	
	}
	
	/**
	 * delete user from the activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public ResponseResult delUserFromActivity(String user_mobile ,Long activity_id){
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
			return new ResponseResult(false, e.toString());
		}		
		return new ResponseResult(true);	
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
	
	//TODO to be tested
	public List<Activity> getUserCollectedActivitiesWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserCollectedActivitiesWithPaging] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize,s);
		return activityRepository.findByCollectingUser(user, pageable).getContent();
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
	 * get collecting user list of an activity with paging
	 * @param activity_id
	 * @return
	 */
	public List<User> getActivityCollectingUsersWithPaging(Long activity_id, int pageno, int pagesize){
		Activity activity = activityRepository.findById(activity_id);
		if (activity == null){
			logger.error("[getActivityCollectingUsers] activity: " + activity_id + "doesn't exist.");
			throw new ActivityNotFoundException(activity_id);
		}
//		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize);
		return userRepository.findByCollectActivity(activity, pageable).getContent();
	}
	
	/**
	 * Add User collected activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public ResponseResult addUserCollectedActivity(String user_mobile , Long activity_id){
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
			return new ResponseResult(false, e.toString());
		}		
		return new ResponseResult(true);	
	}
	
	/**
	 * delete user conllected activity
	 * @param principal
	 * @param activity_id
	 * @return
	 */
	public ResponseResult delUserCollectedActivity(String user_mobile , Long activity_id){
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
			return new ResponseResult(false, e.toString());
		}		
		return new ResponseResult(true);	
	}
	
	
	/**
	 * get user watched organizations
	 * @param principal
	 * @return
	 */
	public Set<Organization> getUserWatchedOrganizations(String user_mobile){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserWatchedOrganizations] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
		return user.getOrganizations();
	}
	
	public List<Organization> getUserWatchedOrganizationsWithPaging(String user_mobile, int pageno, int pagesize){
		User user =userRepository.findByMobile(user_mobile);
		if (user == null){
			logger.error("[getUserWatchedOrganizations] user: " + user_mobile + "doesn't exist.");
			throw new UserNotFoundException(user_mobile);
		}
//		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize);
		return organizationRepository.findByWatchingUser(user, pageable).getContent();
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
	
	public List<User> getOrganizationWatchingUsersWithPaging(String org_mobile, int pageno, int pagesize){
		Organization organization = organizationRepository.findByMobile(org_mobile);
		if (organization == null){
			logger.error("[getOrganizationWatchingUsers] organization: " + org_mobile + "doesn't exist.");
			throw new OrganizationNotFoundException(org_mobile);
		}
//		Sort s=new Sort(Direction.DESC, "createdtime");
		Pageable pageable = new PageRequest(pageno, pagesize);
		return userRepository.findByWatchOrganization(organization, pageable).getContent();
	}
	
	/**
	 * add a watched organization for a user
	 * @param user_mobile
	 * @param organization_id
	 * @return
	 */
	public ResponseResult addUserWatchedOrganization(String user_mobile, Long organization_id){
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
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);	
	}
	
	public ResponseResult delUserWatchedOrganization(String user_mobile, Long organization_id){
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
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);	
	}
	
}
