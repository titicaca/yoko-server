package com.fifteentec.yoko.server.repository;


import com.fifteentec.yoko.server.model.Appointment;
import com.fifteentec.yoko.server.model.User;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "Appointment")
@Qualifier(value = "appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	public Appointment findById(Long id);
	
	public Page<Appointment> findByUser(User user, Pageable pageable);

}
