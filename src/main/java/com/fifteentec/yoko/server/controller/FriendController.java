package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/friend")  
@EnableAutoConfiguration

public class FriendController {
	
	@RequestMapping(method=RequestMethod.POST)
	public Friend addFriend(@RequestBody Friend postclass){
		Friend friend = new Friend();
		friend.setUser_id(postclass.getUser_id());
		friend.setFriend_id(postclass.getFriend_id());
		friend.setTagname(postclass.getTagname());
		return friend;
	}

}
