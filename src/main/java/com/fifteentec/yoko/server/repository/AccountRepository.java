package com.fifteentec.yoko.server.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.Account;

@Repository
@Table(name = "Account")
@Qualifier(value = "accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByUsername(String username);

}
