package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.*;

@Repository
@Table(name = "Schedule")
@Qualifier(value = "scheduleRepository")
public interface ScheduleRepository  extends JpaRepository<Schedule, Long> {
	public Set<Schedule> findByUser_id(Long user_id);
	
}
