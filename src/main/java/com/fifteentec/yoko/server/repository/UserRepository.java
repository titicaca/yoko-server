package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.User;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "User")
@Qualifier(value = "userRepository")
public interface UserRepository  extends JpaRepository<User, Long> {
	public User findById(Long id);
	public User findByMobile(String mobile);
	
	@Query("SELECT u FROM User u INNER JOIN u.enrollactivities a where a = :activity")
	public Page<User> findByEnrollActivity(@Param("activity") Activity activity, Pageable pageable);
	
	@Query("SELECT u FROM User u INNER JOIN u.collectactivities a where a = :activity")
	public Page<User> findByCollectActivity(@Param("activity") Activity activity, Pageable pageable);
	
	@Query("SELECT u FROM User u INNER JOIN u.organizations org where org = :organization")
	public Page<User> findByWatchOrganization(@Param("organization") Organization organization, Pageable pageable);
	
}
