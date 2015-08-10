package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Tag;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.service.FriendService;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/user/mytag")  
@EnableAutoConfiguration
public class TagController {
	
	
	@Autowired
	FriendService friendService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseResult addNewTag(Principal principal, @RequestBody Tag postclass){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.addNewTag(Account.findMobile(principal.getName()), postclass);
	}
	
	@RequestMapping(value="/{tag_id}",method=RequestMethod.PUT)
	public ResponseResult updateTagName(Principal principal, @RequestBody Tag postclass ,@PathVariable("tag_id") Long tag_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.updateTagName(Account.findMobile(principal.getName()), postclass, tag_id);
	}
	
	@RequestMapping(value="/{tag_id}/friends",method=RequestMethod.GET)
	public String getTaggedFriends(Principal principal,@PathVariable("tag_id") Long tag_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User> users = friendService.getTaggedFriends(Account.findMobile(principal.getName()), tag_id);
		return JsonConverterUtil.convertSetToJsonString(users);	
	}
	
	@RequestMapping(value="/{tag_id}/{friend_id}",method=RequestMethod.PUT)
	public ResponseResult addTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.addTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
	}
	
	@RequestMapping(value="/{tag_id}/{friend_id}",method=RequestMethod.DELETE)
	public ResponseResult delTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.delTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
	}
}
