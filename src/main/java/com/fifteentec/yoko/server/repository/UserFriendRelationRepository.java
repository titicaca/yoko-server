package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.UserFriendRelation;

@Repository
@Table(name = "UserFriendRelationRepository")
@Qualifier(value = "userFriendRelationRepository")
public interface UserFriendRelationRepository extends JpaRepository<UserFriendRelation, Long> {
	
	public UserFriendRelation findByUser_idAndFriend_id(Long user_id,Long friend_id);
	public Set<UserFriendRelation> findByUser_id(Long user_id);
	
	
}
