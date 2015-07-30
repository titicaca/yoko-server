package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.Organization;

@Repository
@Table(name = "Organization")
@Qualifier(value = "organizationRepository")
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
	
	public Organization findById(Long id);
	public Organization findByMobile(String mobile);
	
}
