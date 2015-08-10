package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
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
import com.fifteentec.yoko.server.service.FriendService;
import com.fifteentec.yoko.server.util.ContainerToJsonStringConverter;

@RestController  
@RequestMapping("/user/mytag")  
@EnableAutoConfiguration
public class TagController {
	
	
	@Autowired
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> addTag(Principal principal, @RequestBody Tag postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.addNewTag(Account.findMobile(principal.getName()), postclass);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/{tag_id}",method=RequestMethod.PUT)
	public ResponseEntity<String> updateTagName(Principal principal, @RequestBody Tag postclass ,@PathVariable("tag_id") Long tag_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.updateTagName(Account.findMobile(principal.getName()), postclass, tag_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/{tag_id}/friends",method=RequestMethod.GET)
	public String getTaggedFriends(Principal principal,@PathVariable("tag_id") Long tag_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User> users = friendService.getTaggedFriends(Account.findMobile(principal.getName()), tag_id);
		return ContainerToJsonStringConverter.convertSetToJsonString(users);	
	}
	
	@RequestMapping(value="/{tag_id}/{friend_id}",method=RequestMethod.PUT)
	public ResponseEntity<String> addTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.addTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/{tag_id}/{friend_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.delTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
		return new Result(r).getResponseResult();
	}
}
