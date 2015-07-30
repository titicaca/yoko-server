package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
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
	
	@RequestMapping(value="/byuser",method=RequestMethod.GET)
	public Set<String> getOrganizationsNameByUser(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Set<String> organizationsName = new HashSet<String>();
		for (Organization organization : user.getOrganizations()) {
			organizationsName.add(organization.getName());
		}
		return organizationsName;
	}
	
	@RequestMapping(value="/byorganization",method=RequestMethod.GET)
	public Set<String> getUserByActivity(Principal principal){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		Organization organization = organizationRepository.findByMobile(Account.findMobile(principal.getName()));
		Set<String> usersMobile = new HashSet<String>();
		for(User user:organization.getUsers()){
			usersMobile.add(user.getMobile());
		}
		return usersMobile;
	}
	
	@RequestMapping(value="/{organization_id}",method=RequestMethod.POST)
	public Result addUserEnrollOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Organization organization = organizationRepository.findById(organization_id);	
		try{
			organization.getUsers().add(user);
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);	
	}
	
	@RequestMapping(value="/{organization_id}",method=RequestMethod.DELETE)
	public Result delUserEnrollOrganization(Principal principal ,@PathVariable("organization_id") Long organization_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Organization organization = organizationRepository.findById(organization_id);
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
