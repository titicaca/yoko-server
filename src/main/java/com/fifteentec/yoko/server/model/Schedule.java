package com.fifteentec.yoko.server.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Schedule")
public class Schedule extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timebegin")
	private Date timebegin;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timeend")
	private Date timeend;
	
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
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdtime")
	private Date createdtime = new Date();
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatedtime")
	private Date updatedTime = new Date();
	
	
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	@JsonIgnore
	private User user;
	
	public Schedule(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimebegin() {
		return timebegin;
	}

	public void setTimebegin(Date timebegin) {
		this.timebegin = timebegin;
	}

	public Date getTimeend() {
		return timeend;
	}

	public void setTimeend(Date timeend) {
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
	
	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}   
	
	public Date getUpdatedtime() {
		return this.updatedTime;
	}

	public void setUpdatedtime(Date time) {
		this.updatedTime = time;
	}  
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}   
	
	
	

}
