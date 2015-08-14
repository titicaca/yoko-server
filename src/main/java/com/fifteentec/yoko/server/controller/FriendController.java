package com.fifteentec.yoko.server.controller;

import java.security.Principal;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.service.FriendService;
import com.fifteentec.yoko.server.util.JsonConverterUtil;
import com.fifteentec.yoko.server.util.ResponseResult;

@RestController  
@RequestMapping("/user/myfriend")  
@EnableAutoConfiguration

public class FriendController {
	
	@Autowired
	FriendService friendService;
	
	
	@RequestMapping(value="/request/{friend_id}",method=RequestMethod.POST)
	public ResponseResult addUserRequestFriend(Principal principal , @PathVariable("friend_id") Long friend_id, @RequestBody Msg msg){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return  friendService.addUserFriendRequest(Account.findMobile(principal.getName()), friend_id, msg.getMsg());
	}
	
	@RequestMapping(value="/{friend_id}",method=RequestMethod.DELETE)
	public ResponseResult delFriend(Principal principal , @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.delFriendRelation(Account.findMobile(principal.getName()), friend_id);
	}
	
	@RequestMapping(value="/response/{friend_id}",method=RequestMethod.PUT)
	public ResponseResult responseUserRequestFriend(Principal principal,@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return  friendService.acceptUserFriendRequest(Account.findMobile(principal.getName()), friend_id);
	}
	
	@RequestMapping(value="/response/{friend_id}",method=RequestMethod.DELETE)
	public ResponseResult delUserRequestFriend(Principal principal,@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return  friendService.rejectUserRequestFriend(Account.findMobile(principal.getName()), friend_id);
	}
	
	@RequestMapping(value="/{friend_id}/tags",method=RequestMethod.GET)
	public String getFriendTags(Principal principal, @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<Tag> tags = friendService.getFriendTags(Account.findMobile(principal.getName()), friend_id);
		return JsonConverterUtil.convertSetToJsonString(tags);	
	}
	
	@RequestMapping(value="/{friend_id}/{tag_id}",method=RequestMethod.PUT)
	public ResponseResult addTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.addTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
	}
	
	@RequestMapping(value="/{friend_id}/{tag_id}",method=RequestMethod.DELETE)
	public ResponseResult delTaggedFriend(Principal principal, @PathVariable("tag_id") Long tag_id, 
			@PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return friendService.delTaggedFriend(Account.findMobile(principal.getName()), tag_id, friend_id);
	}
	
	@RequestMapping(value="/friends",method=RequestMethod.GET)
	public String getFriends(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<User>friends =  friendService.getFriends(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(friends);
	}
	
	@RequestMapping(value="/allinfo",method=RequestMethod.GET)
	public String getFriendsAndTags(Principal principal){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		Set<UserFriendRelation>friends =  friendService.getFriendsAndTags(Account.findMobile(principal.getName()));
		return JsonConverterUtil.convertSetToJsonString(friends);
	}
	
	@RequestMapping(value="/{friend_id}/info",method=RequestMethod.GET)
	public User getFriendInfo(Principal principal, @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return  friendService.getFriendInfo(Account.findMobile(principal.getName()), friend_id);
	}	
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public User searchFriend(Principal principal, @RequestBody User user){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		return  friendService.searchFriend(Account.findMobile(principal.getName()), user.getMobile());
	}
}

class Msg{
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}



