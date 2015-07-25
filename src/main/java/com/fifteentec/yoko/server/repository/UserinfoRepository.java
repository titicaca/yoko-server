package com.fifteentec.yoko.server.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.*;

@Repository
@Table(name = "Userinfo")
@Qualifier(value = "userinfoRepository")
public interface UserinfoRepository  extends JpaRepository<Userinfo, Long>{
	
	public Userinfo findByUser_id(Long user_id);
	
	

}
