package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.repository.OrganizationRepository;

@RestController  
@RequestMapping("/organization")  
@EnableAutoConfiguration
public class OrganizationController {
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<Organization> getAllOrganizations(){
		return organizationRepository.findAll();
	}
	
	@RequestMapping(value="/{organization_id}",method=RequestMethod.GET)
	public Organization getOrganization(@PathVariable("organization_id") Long organization_id){
		return organizationRepository.findById(organization_id);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Result addOrganization(Principal principal ,@RequestBody Organization postclass){
		if(!Account.findRole(principal.getName()).equals("1")) throw new PermissionErrorException();
		Organization organization = organizationRepository.findByMobile(Account.findMobile(principal.getName()));
		organization.setName(postclass.getName());
		organization.setType(postclass.getType());
		organization.setMobile(postclass.getMobile());
		organization.setPicturelink(postclass.getPicturelink());
		organization.setIntroduction(postclass.getIntroduction());
		organization.setRealname(postclass.getRealname());
		organization.setCard(postclass.getCard());
		organization.setAddress(postclass.getAddress());
		organization.setPhotolink(postclass.getPhotolink());
		try{
			organizationRepository.save(organization);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
}
