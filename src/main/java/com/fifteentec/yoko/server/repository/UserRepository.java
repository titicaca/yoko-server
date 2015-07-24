package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.User;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "User")
@Qualifier(value = "userRepository")
public interface UserRepository  extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	public User findById(Long id);
	public User findByMobileAndPassword(String mobile, String password);
//	public User findByUser(User user);
	
}
