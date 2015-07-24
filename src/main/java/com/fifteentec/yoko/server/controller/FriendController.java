package com.fifteentec.yoko.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.UserRepository;

@RestController  
@RequestMapping("/friend")  
@EnableAutoConfiguration

public class FriendController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Result addUserInviteFriend(@RequestBody UserAndFriend postclass){
		User user = userRepository.findById(postclass.getUser_id());
		try{
			user.getAddfriends().add(userRepository.findById(postclass.getFriend_id()));
			userRepository.save(user);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="response",method=RequestMethod.POST)
	public Result addUserFriend(@RequestBody UserAndFriend postclass){
		User user = userRepository.findById(postclass.getUser_id());
		try{
			user.getFriends().add(userRepository.findById(postclass.getFriend_id()));
			userRepository.save(user);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="response",method=RequestMethod.DELETE)
	public Result delUserInviteFriend(@RequestBody UserAndFriend postclass){
		User user = userRepository.findById(postclass.getUser_id());
		try{
			user.getAddfriends().remove(userRepository.findById(postclass.getFriend_id()));
			userRepository.save(user);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}

class UserAndFriend {
	private Long user_id;
	private Long friend_id;
	public Long getUser_id() {
		return user_id;
	}
	public Long getFriend_id() {
		return friend_id;
	}
	
}
