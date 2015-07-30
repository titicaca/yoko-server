package com.fifteentec.yoko.server.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.fifteentec.yoko.server.exception.PermissionErrorException;
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
	
	@RequestMapping(value="/{friend_id}",method=RequestMethod.POST)
	public Result addUserRequestFriend(Principal principal , @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		if(userRequestFriendRepository.findByUser_idAndFriend_id(user.getId(), friend_id)!=null) return new Result(false);
		
		UserRequestFriend userRequestFriend = new UserRequestFriend();
		try{
			userRequestFriend.setUser(user);
			userRequestFriend.setFriend(userRepository.findById(friend_id));
			userRequestFriendRepository.save(userRequestFriend);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="/{friend_id}",method=RequestMethod.DELETE)
	public Result delFriend(Principal principal , @PathVariable("friend_id") Long friend_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User user =userRepository.findByMobile(Account.findMobile(principal.getName()));
		
		UserFriendRelation userFriendRelation = userFriendRelationRepository.findByUser_idAndFriend_id(user.getId(), friend_id);
		
		try{
			userFriendRelationRepository.delete(userFriendRelation);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="response/{user_id}",method=RequestMethod.PUT)
	public Result updateUserRequestFriend(Principal principal,@PathVariable("user_id") Long user_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User friend =userRepository.findByMobile(Account.findMobile(principal.getName()));
		
		try{
			UserRequestFriend userRequestFriend = userRequestFriendRepository.findByUser_idAndFriend_id(user_id, friend.getId());
			userRequestFriendRepository.delete(userRequestFriend);
		
			UserFriendRelation userFriendRelation = new UserFriendRelation();	
			userFriendRelation.setUser(userRepository.findById(user_id));
			userFriendRelation.setFriend(friend);
			userFriendRelationRepository.save(userFriendRelation);
		
			UserFriendRelation userFriendRelation2 = new UserFriendRelation();	
			userFriendRelation2.setUser(friend);
			userFriendRelation2.setFriend(userRepository.findById(user_id));
			userFriendRelationRepository.save(userFriendRelation2);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}
	
	@RequestMapping(value="response/{user_id}",method=RequestMethod.DELETE)
	public Result delUserRequestFriend(Principal principal,@PathVariable("user_id") Long user_id){
		if(!Account.findRole(principal.getName()).equals("0")) throw new PermissionErrorException();
		User friend =userRepository.findByMobile(Account.findMobile(principal.getName()));
		
		UserRequestFriend userRequestFriend = userRequestFriendRepository.findByUser_idAndFriend_id(user_id, friend.getId());
		try{
			userRequestFriendRepository.delete(userRequestFriend);
		}
		catch(Exception e){
			return new Result(false);
		}
		return new Result(true);
	}

}

