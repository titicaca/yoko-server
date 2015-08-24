package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.Activity;
import com.fifteentec.yoko.server.model.Organization;

import java.util.Collection;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "Activity")
@Qualifier(value = "activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	
	public Activity findById(Long id);
	
	public Page<Activity> findByOrganizationIn(Collection<Organization> organizations, Pageable pageable);
}
