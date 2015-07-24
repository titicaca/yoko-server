package com.fifteentec.yoko.server.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/enrollorganization")  
@EnableAutoConfiguration
public class EnrollOrganizationController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrganizationRepository organizationRepository;
	
	@RequestMapping(value="/byuser/{user_id}",method=RequestMethod.GET)
	public Set<String> getOrganizationsNameByUser(@PathVariable("user_id") Long user_id){
		User user = userRepository.findById(user_id);
		Set<String> organizationsName = new HashSet<String>();
		for (Organization organization : user.getOrganizations()) {
			organizationsName.add(organization.getName());
		}
		return organizationsName;
	}
	
	@RequestMapping(value="/byorganization/{organization_id}",method=RequestMethod.GET)
	public Set<String> getUserByActivity(@PathVariable("organization_id") Long organization_id){
		Organization organization = organizationRepository.findById(organization_id);
		Set<String> usersMobile = new HashSet<String>();
		for(User user:organization.getUsers()){
			usersMobile.add(user.getMobile());
		}
		return usersMobile;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addUserEnrollOrganization(@RequestBody UserAndOrganization postclass){
		Organization organization = organizationRepository.findById(postclass.getOrganization_id());
		User user = userRepository.findById(postclass.getUser_id());	
		try{
			organization.getUsers().add(user);
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public Result delUserEnrollOrganization(@RequestBody UserAndOrganization postclass){
		Organization organization = organizationRepository.findById(postclass.getOrganization_id());
		User user = userRepository.findById(postclass.getUser_id());	
		try{
			organization.getUsers().remove(user);
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}

}

class UserAndOrganization{
	Long user_id;
	Long organization_id;
	public Long getUser_id() {
		return user_id;
	}
	public Long getOrganization_id() {
		return organization_id;
	}
	
}