package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.FriendService;
import com.fifteentec.yoko.server.util.ContainerToJsonStringConverter;

@RestController  
@RequestMapping("/user/myfriend")  
@EnableAutoConfiguration

public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	
	@RequestMapping(value="/request/{friend_id}",method=RequestMethod.POST)
	public ResponseEntity<String> addUserRequestFriend(Principal principal , @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.addUserFriendRequest(Account.findMobile(principal.getName()), friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/request/{friend_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delFriend(Principal principal , @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.delFriendRelation(Account.findMobile(principal.getName()), friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/response/{friend_id}",method=RequestMethod.PUT)
	public ResponseEntity<String> responseUserRequestFriend(Principal principal,@PathVariable("user_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.acceptUserFriendRequest(Account.findMobile(principal.getName()), friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/response/{friend_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delUserRequestFriend(Principal principal,@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.rejectUserRequestFriend(Account.findMobile(principal.getName()), friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/{friend_id}/tags",method=RequestMethod.GET)
	public String getFriendTags(Principal principal, @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Tag> tags = friendService.getFriendTags(Account.findMobile(principal.getName()), friend_id);
		return ContainerToJsonStringConverter.convertSetToJsonString(tags);	
	}
	
	@RequestMapping(value="/{friend_id}/{tag_id}",method=RequestMethod.PUT)
	public ResponseEntity<String> addTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.addTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
		return new Result(r).getResponseResult();
	}
	
	@RequestMapping(value="/{friend_id}/{tag_id}",method=RequestMethod.DELETE)
	public ResponseEntity<String> delTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Boolean r = friendService.delTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
		return new Result(r).getResponseResult();
	}

}

