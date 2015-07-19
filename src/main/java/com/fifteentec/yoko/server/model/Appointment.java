package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="Appointment")
public class Appointment extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Version
	private Long version;
	@Column(name="name")
	private String name;
	//TODO appointment_id is same as ID?
	@Column(name="appointment_id",nullable=false)
	private int appointment_id;
	@Column(name="timebegin",columnDefinition="TIMESTAMP")
	private String timebegin;
	@Column(name="timeend",columnDefinition="TIMESTAMP")
	private String timeend;
	@Column(name="location")
	private String location;
	@Column(name="introduction")
	private String introduction;
	@Column(name="peopleall")
	private int peopleall;
	@Column(name="picturelink")
	private String picturelink;
	@Column(name="detaillink")
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
