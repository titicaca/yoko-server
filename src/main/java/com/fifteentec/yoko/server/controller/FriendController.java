package com.fifteentec.yoko.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.model.*;
import com.fifteentec.yoko.server.repository.UserFriendRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.repository.UserRequestFriendRepository;

@RestController  
@RequestMapping("/friend")  
@EnableAutoConfiguration

public class FriendController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRequestFriendRepository userRequestFriendRepository;
	@Autowired
	UserFriendRelationRepository userFriendRelationRepository;
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Result addUserRequestFriend(@RequestBody UserAndFriend postclass){
		if(userRequestFriendRepository.findByUser_idAndFriend_id(postclass.getUser_id(), postclass.getFriend_id())!=null) return new Result(false);
		UserRequestFriend userRequestFriend = new UserRequestFriend();
		try{
			userRequestFriend.setUser(userRepository.findById(postclass.getUser_id()));
			userRequestFriend.setFriend(userRepository.findById(postclass.getFriend_id()));
			userRequestFriendRepository.save(userRequestFriend);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="response",method=RequestMethod.PUT)
	public Result updateUserRequestFriend(@RequestBody UserAndFriend postclass){
		UserRequestFriend userRequestFriend = userRequestFriendRepository.findByUser_idAndFriend_id(postclass.getUser_id(), postclass.getFriend_id());
		UserFriendRelation userFriendRelation = new UserFriendRelation();
		try{
		//	if(userRequestFriend == null) return new Result(false);

			
			
		}
		catch(Exception e){
			return new Result(false);
		}
		
		userRequestFriendRepository.delete(userRequestFriend);
		userFriendRelation.setUser(userRepository.findById(postclass.getUser_id()));
		userFriendRelation.setFriend(userRepository.findById(postclass.getFriend_id()));
		userFriendRelationRepository.save(userFriendRelation);
		
		return new Result(true);
	}
	
	@RequestMapping(value="response",method=RequestMethod.DELETE)
	public Result delUserRequestFriend(@RequestBody UserAndFriend postclass){
		UserRequestFriend userRequestFriend = userRequestFriendRepository.findByUser_idAndFriend_id(postclass.getUser_id(), postclass.getFriend_id());
		try{
			userRequestFriendRepository.delete(userRequestFriend);
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
