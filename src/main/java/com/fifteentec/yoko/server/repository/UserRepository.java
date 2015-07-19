package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.User;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "users")
@Qualifier(value = "userRepository")
public interface UserRepository  extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
