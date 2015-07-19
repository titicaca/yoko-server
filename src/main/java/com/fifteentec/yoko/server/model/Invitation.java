package com.fifteentec.yoko.server.model;

public class Invitation extends BaseModel {
	private int id;
	private int appointment_id;
	private int user_id;
	private int friend_id;
	private int type;
	
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
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getFriend_id() {
		return friend_id;
	}
	
	public void setFriend_id(int friend_id) {
		this.friend_id = friend_id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
}
