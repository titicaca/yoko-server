package com.fifteentec.yoko.server.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;

@RestController  
@RequestMapping("/newfriend")  
@EnableAutoConfiguration

public class NewFriendController {
	
	@RequestMapping(method=RequestMethod.POST)
	public NewFriend addNewFriend(@RequestBody NewFriend postclass){
		NewFriend newfriend = new NewFriend();
		newfriend.setUser_id1(postclass.getUser_id1());
		newfriend.setUser_id2(postclass.getUser_id2());
		return newfriend;
	}
}
