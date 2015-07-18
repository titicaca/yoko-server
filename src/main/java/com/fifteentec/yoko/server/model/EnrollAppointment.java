package com.fifteentec.yoko.server.model;

public class EnrollAppointment extends BaseModel{
	private int user_id;
	private int appointment_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	
	
}
