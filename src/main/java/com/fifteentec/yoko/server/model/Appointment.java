package com.fifteentec.yoko.server.model;


public class Appointment extends BaseModel{
	private int id;
	private String name;
	private int appointment_id;
	private String timebegin;
	private String timeend;
	private String location;
	private String introduction;
	private int peopleall;
	private String picturelink;
	private String detaillink;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

	public String getTimebegin() {
		return timebegin;
	}

	public void setTimebegin(String timebegin) {
		this.timebegin = timebegin;
	}

	public String getTimeend() {
		return timeend;
	}

	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getPeopleall() {
		return peopleall;
	}

	public void setPeopleall(int peopleall) {
		this.peopleall = peopleall;
	}

	public String getPicturelink() {
		return picturelink;
	}

	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}

	public String getDetaillink() {
		return detaillink;
	}

	public void setDetaillink(String detaillink) {
		this.detaillink = detaillink;
	}	

}
