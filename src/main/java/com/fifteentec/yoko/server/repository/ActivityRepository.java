package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.Activity;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "Activity")
@Qualifier(value = "activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	public Activity findByActivityname(String activityname);
}
