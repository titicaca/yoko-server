package com.fifteentec.yoko.server.model;

public class AppointmentPeople {
	private int id;
	private int appointment_id;
	private int peopleenroll;
	
	public AppointmentPeople(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

	public int getPeopleenroll() {
		return peopleenroll;
	}

	public void setPeopleenroll(int peopleenroll) {
		this.peopleenroll = peopleenroll;
	}
	
	

}
