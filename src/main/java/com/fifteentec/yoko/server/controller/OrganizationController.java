package com.fifteentec.yoko.server.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

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
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addOrganization(@RequestBody Organization postclass){
		Organization organization = new Organization();
		organization.setName(postclass.getName());
		organization.setType(postclass.getType());
		organization.setPicturelink(postclass.getPicturelink());
		organization.setIntroduction(postclass.getIntroduction());
		organization.setMobile(postclass.getMobile());
		organization.setPassword(postclass.getPassword());
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
