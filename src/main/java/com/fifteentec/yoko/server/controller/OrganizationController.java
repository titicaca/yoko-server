package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.fifteentec.yoko.server.exception.OrganizationNotFoundException;
import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.repository.ActivityRepository;
import com.fifteentec.yoko.server.repository.OrganizationRepository;
import com.fifteentec.yoko.server.service.AccountService;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/organization")  
@EnableAutoConfiguration
public class OrganizationController {
	
	@Autowired
	AccountService accountService;
	
	private Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
	//TODO
	//is it necessary?
//	@RequestMapping(value="/all",method=RequestMethod.GET)
//	public List<Organization> getAllOrganizations(){
//		return organizationRepository.findAll();
//	}
	
	/**
	 * Get organization info
	 * @param principal
	 * @return organization object
	 */
	@RequestMapping(value="/orginfo",method=RequestMethod.GET)
	public Organization getOrganization(Principal principal){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[getOrganization] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return accountService.getOrganization(Account.findMobile(principal.getName()));
	}
	
	/**
	 * Update organization 
	 * @param principal
	 * @param postclass
	 * @return Boolean f
	 */
	@RequestMapping(value="/orginfo", method=RequestMethod.PUT)
	public ResponseResult updateOrganization(Principal principal ,@RequestBody Organization postclass){
		if(!Account.findRole(principal.getName()).equals("1")) {
			logger.error("[updateOrganization] org: "+ principal.getName() + " permission denied");
			throw new PermissionErrorException();
		}
		return accountService.updateOrganizationInfo(Account.findMobile(principal.getName()), postclass);
	}
	
	
	
	
}
