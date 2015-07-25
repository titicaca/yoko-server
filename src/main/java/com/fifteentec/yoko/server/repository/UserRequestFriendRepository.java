package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.UserRequestFriend;

@Repository
@Table(name = "UserRequestFriendRepository")
@Qualifier(value = "userRequestFriendRepository")
public interface UserRequestFriendRepository extends JpaRepository<UserRequestFriend, Long> {
	
	public UserRequestFriend findByUser_idAndFriend_id(Long user_id,Long friend_id);
	public Set<UserRequestFriend> findByUser_id(Long user_id);
	
	
}
