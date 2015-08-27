package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.Organization;
import com.fifteentec.yoko.server.model.User;

@Repository
@Table(name = "Organization")
@Qualifier(value = "organizationRepository")
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	public Organization findById(Long id);
	public Organization findByMobile(String mobile);
	public Page<Organization> findByNameLike(String name, Pageable page);
	
	@Query("SELECT organization from Organization organization INNER JOIN organization.users u WHERE u = :user")
	public Page<Organization> findByWatchingUser(User user, Pageable page);
	
}
