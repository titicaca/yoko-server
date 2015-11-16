package com.fifteentec.yoko.server.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Activity")
public class Activity extends BaseModel{
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
	
	@Column(name="introduction")
	private String introduction;
	
	@Column(name="peopleenroll",columnDefinition = "INT default 0")
	private int peopleenroll;
	
	@Column(name="peopleall")
	private int peopleall;
	
	@Column(name="picturelink")
	private String picturelink;
	
	@Column(name="detaillink")
	private String detaillink;
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdtime")
	private Date createdtime = new Date();
	
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;	
	
	@Column(name="type", columnDefinition = "INT default 0")
	private int type;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	@JsonIgnore
	private Organization organization;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="UserCollectActivity",
		joinColumns={@JoinColumn(name="activity_id")},
		inverseJoinColumns={@JoinColumn(name="user_id")})
	@JsonIgnore
	private Set<User> collectusers;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="UserEnrollActivity",
		joinColumns={@JoinColumn(name="activity_id")},
		inverseJoinColumns={@JoinColumn(name="user_id")})
	@JsonIgnore 
	private Set<User> enrollusers;
	
	public Activity(){
		
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public int getPeopleenroll() {
		return peopleenroll;
	}

	public void setPeopleenroll(int peopleenroll) {
		this.peopleenroll = peopleenroll;
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
	
	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}   
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<User> getCollectusers() {
		return collectusers;
	}

	public void setCollectusers(Set<User> collectusers) {
		this.collectusers = collectusers;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<User> getEnrollusers() {
		return enrollusers;
	}

	public void setEnrollusers(Set<User> enrollusers) {
		this.enrollusers = enrollusers;
	}

	
	
	

	
	
	
	

}
