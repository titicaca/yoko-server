package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AppointmentPeople")
public class AppointmentPeople {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="appointment_id")
	private Long appointment_id;
	@Column(name="peopleenrolls")
	private int peopleenroll;
	
	public AppointmentPeople(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}

	public int getPeopleenroll() {
		return peopleenroll;
	}

	public void setPeopleenroll(int peopleenroll) {
		this.peopleenroll = peopleenroll;
	}
	
	

}
