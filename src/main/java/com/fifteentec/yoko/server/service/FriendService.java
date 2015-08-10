package com.fifteentec.yoko.server.service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fifteentec.yoko.server.exception.FriendRelationNotFoundException;
import com.fifteentec.yoko.server.exception.FriendTagNotFoundException;
import com.fifteentec.yoko.server.exception.PermissionErrorException;
import com.fifteentec.yoko.server.exception.UserNotFoundException;
import com.fifteentec.yoko.server.model.Account;
import com.fifteentec.yoko.server.model.Tag;
import com.fifteentec.yoko.server.model.User;
import com.fifteentec.yoko.server.model.UserFriendRelation;
import com.fifteentec.yoko.server.model.UserRequestFriend;
import com.fifteentec.yoko.server.repository.TagRepository;
import com.fifteentec.yoko.server.repository.UserFriendRelationRepository;
import com.fifteentec.yoko.server.repository.UserRepository;
import com.fifteentec.yoko.server.repository.UserRequestFriendRepository;
import com.fifteentec.yoko.server.util.ResponseResult;

@Service
public class FriendService {
	
	private Logger logger = LoggerFactory.getLogger(FriendService.class);
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRequestFriendRepository userRequestFriendRepository;
	@Autowired
	UserFriendRelationRepository userFriendRelationRepository;
	@Autowired
	TagRepository tagRepository;
	
	/**
	 * add user friend request
	 * @param user_mobile
	 * @param friend_id
	 * @return
	 */
	public ResponseResult addUserFriendRequest(String user_mobile , Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addUserFriendRequest] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		if(userRequestFriendRepository.findByUser_idAndFriend_id(user.getId(), friend_id)!=null) 
			return new ResponseResult(false, "request doesn't exist");
		UserRequestFriend userRequestFriend = new UserRequestFriend();
		try{
			userRequestFriend.setUser(user);
			userRequestFriend.setFriend(userRepository.findById(friend_id));
			userRequestFriendRepository.save(userRequestFriend);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * delete user friend relation
	 * @param user_mobile
	 * @param friend_id
	 * @return
	 */
	public ResponseResult delFriendRelation(String user_mobile , Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[delFriendRelation] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		UserFriendRelation userFriendRelation = userFriendRelationRepository.findByUser_idAndFriend_id(user.getId(), friend_id);
		if(userFriendRelation == null){
			logger.error("[delFriendRelation] userFriendRelation: [" + user.getId() + ", " + friend_id + "] doesn't exist; "  );
			throw new FriendRelationNotFoundException(user.getId(), friend_id);
		}
		try{
			userFriendRelationRepository.delete(userFriendRelation);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * response the user friend request
	 * @param user_mobile
	 * @param friend_id
	 * @return
	 */
	public ResponseResult acceptUserFriendRequest(String user_mobile, Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[updateUserRequestFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		try{
			UserRequestFriend userRequestFriend = userRequestFriendRepository.findByUser_idAndFriend_id(friend_id, user.getId());
			userRequestFriendRepository.delete(userRequestFriend);
		
			UserFriendRelation userFriendRelation = new UserFriendRelation();	
			userFriendRelation.setUser(userRepository.findById(friend_id));
			userFriendRelation.setFriend(user);
			userFriendRelationRepository.save(userFriendRelation);
		
			UserFriendRelation userFriendRelation2 = new UserFriendRelation();	
			userFriendRelation2.setUser(user);
			userFriendRelation2.setFriend(userRepository.findById(friend_id));
			userFriendRelationRepository.save(userFriendRelation2);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * delete user 
	 * @param user_mobile
	 * @param user_id
	 * @return
	 */
	public ResponseResult rejectUserRequestFriend(String user_mobile, Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[rejectUserRequestFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		try{
			UserRequestFriend userRequestFriend = 
				userRequestFriendRepository.findByUser_idAndFriend_id(friend_id, user.getId());
			userRequestFriendRepository.delete(userRequestFriend);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * add a new user tag
	 * @param user_mobile
	 * @param postclass
	 * @return
	 */
	public ResponseResult addNewTag(String user_mobile, Tag postclass){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addNewTag] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Tag tag = new Tag();
		tag.setTagname(postclass.getTagname());
		tag.setUser(user);
		try{
			tagRepository.save(tag);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * update user tag
	 * @param user_mobile
	 * @param postclass
	 * @param tag_id
	 * @return
	 */
	public ResponseResult updateTagName(String user_mobile, Tag postclass, Long tag_id){
		Tag tag = tagRepository.findById(tag_id);
		if(tag == null){
			logger.error("[updateTag] tag:" + tag_id + "doesn't exist; "  );
			throw new FriendTagNotFoundException(tag_id);
		}
		tag.setTagname(postclass.getTagname());
		try{
			tagRepository.save(tag);
		}
		catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * add friend tag relation
	 * @param user_mobile
	 * @param tag_id
	 * @param friend_id
	 * @return
	 */
	public ResponseResult addTaggedFriend(String user_mobile, Long tag_id, Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addTaggedFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Tag tag = tagRepository.findById(tag_id);
		if(tag == null){
			logger.error("[addTaggedFriend] tag:" + tag_id + "doesn't exist; "  );
			throw new FriendTagNotFoundException(tag_id);
		}
		UserFriendRelation userFriendRelation =  userFriendRelationRepository.findByUser_idAndFriend_id(user.getId(), friend_id);
		if(userFriendRelation == null){
			logger.error("[addTaggedFriend] userFriendRelation: [" + user.getId() + ", " + friend_id + "] doesn't exist; "  );
			throw new FriendRelationNotFoundException(user.getId(), friend_id);
		}
		try{
			tag.getUserFriendRelations().add(userFriendRelation);
			tagRepository.save(tag);
		}catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * del friend tag relation
	 * @param user_mobile
	 * @param tag_id
	 * @param friend_id
	 * @return
	 */
	public ResponseResult delTaggedFriend(String user_mobile, Long tag_id, Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addTaggedFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Tag tag = tagRepository.findById(tag_id);
		if(tag == null){
			logger.error("[addTaggedFriend] tag:" + tag_id + "doesn't exist; "  );
			throw new FriendTagNotFoundException(tag_id);
		}
		UserFriendRelation userFriendRelation =  userFriendRelationRepository.findByUser_idAndFriend_id(user.getId(), friend_id);
		if(userFriendRelation == null){
			logger.error("[addTaggedFriend] userFriendRelation: [" + user.getId() + ", " + friend_id + "] doesn't exist; "  );
			throw new FriendRelationNotFoundException(user.getId(), friend_id);
		}
		try{
			tag.getUserFriendRelations().remove(userFriendRelation);
			tagRepository.save(tag);
		}catch(Exception e){
			return new ResponseResult(false, e.toString());
		}
		return new ResponseResult(true);
	}
	
	/**
	 * get user friend tags
	 * @param user_mobile
	 * @param friend_id
	 * @return
	 */
	public Set<Tag> getFriendTags(String user_mobile, Long friend_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addTaggedFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		UserFriendRelation userFriendRelation =  userFriendRelationRepository.findByUser_idAndFriend_id(user.getId(), friend_id);
		if(userFriendRelation == null){
			logger.error("[addTaggedFriend] userFriendRelation: [" + user.getId() + ", " + friend_id + "] doesn't exist; "  );
			throw new FriendRelationNotFoundException(user.getId(), friend_id);
		}
		return userFriendRelation.getTags();
	}
	
	/**
	 * get the tagged friends
	 * @param user_mobile
	 * @param tag_id
	 * @return
	 */
	public Set<User> getTaggedFriends(String user_mobile, Long tag_id){
		User user =userRepository.findByMobile(user_mobile);
		if(user == null){
			logger.error("[addTaggedFriend] user:" + user_mobile + "doesn't exist; "  );
			throw new UserNotFoundException(user_mobile);
		}
		Tag tag = tagRepository.findById(tag_id);
		if(tag == null){
			logger.error("[addTaggedFriend] tag:" + tag_id + "doesn't exist; "  );
			throw new FriendTagNotFoundException(tag_id);
		}
		Set<User> users = new HashSet<User>();
		for(UserFriendRelation r: tag.getUserFriendRelations()){
			users.add(r.getFriend());
		}
		return users;
	}
	
	

}
