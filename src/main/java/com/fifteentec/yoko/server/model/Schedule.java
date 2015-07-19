package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Schedule")
public class Schedule extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="user_id")
	private int user_id;
	@Column(name="timebegin",columnDefinition="TIMESTAMP")
	private String timebegin;
	@Column(name="timeend",columnDefinition="TIMESTAMP")
	private String timeend;
	@Column(name="location")
	private String location;
	@Column(name="type")
	private int type;
	@Column(name="remind")
	private int remind;
	@Column(name="duplication")
	private int duplication;
	@Column(name="introduction")
	private String introduction;
	@Column(name="property")
	private int property;
	@Column(name="detaillink")
	private String detaillink;
	
	public Schedule(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRemind() {
		return remind;
	}

	public void setRemind(int remind) {
		this.remind = remind;
	}

	public int getDuplication() {
		return duplication;
	}

	public void setDuplication(int duplication) {
		this.duplication = duplication;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public String getDetaillink() {
		return detaillink;
	}

	public void setDetaillink(String detaillink) {
		this.detaillink = detaillink;
	}
	
	

}
