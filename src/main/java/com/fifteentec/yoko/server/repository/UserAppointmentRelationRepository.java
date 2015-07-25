package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.UserAppointmentRelation;

@Repository
@Table(name = "UserAppointmentRelation")
@Qualifier(value = "userAppointmentRelationRepository")
public interface UserAppointmentRelationRepository extends JpaRepository<UserAppointmentRelation, Long> {
	
	public UserAppointmentRelation findByUser_idAndAppointment_id(Long user_id,Long appointment_id);
	public Set<UserAppointmentRelation> findByUser_id(Long user_id);
	
	
}
