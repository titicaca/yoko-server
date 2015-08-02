package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Result;
import com.fifteentec.yoko.server.model.Tag;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.repository.TagRepository;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/tag")  
@EnableAutoConfiguration
public class TagController {
	
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	TagRepository tagRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public Result addTag(Principal principal, @RequestBody Tag postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		Tag tag = new Tag();
		tag.setTagname(postclass.getTagname());
		tag.setUser(user);
		try{
			tagRepository.save(tag);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="/{tag_id}",method=RequestMethod.PUT)
	public Result updateTag(Principal principal, @RequestBody Tag postclass ,@PathVariable("tag_id") Long tag_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Tag tag = tagRepository.findById(tag_id);
		tag.setTagname(postclass.getTagname());
		try{
			tagRepository.save(tag);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}
