package com.fifteentec.yoko.server.model;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Appointment")
public class Appointment extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(name="name")
	private String name;
	
	//TODO appointment_id is same as ID?
	//@Column(name="appointment_id",nullable=false)
	//private int appointment_id;
	
	@Column(name="timebegin",columnDefinition="TIMESTAMP")
	private String timebegin;
	
	@Column(name="timeend",columnDefinition="TIMESTAMP")
	private String timeend;
	
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
	
	/*
	 * invitation status
	 * 0 -- default
	 * 1 -- accept
	 * 2 -- reject
	 * 3 -- uncertain
	 */
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="UserEnrollAppointment",
//		joinColumns={@JoinColumn(name="appointment_id")},
//		inverseJoinColumns={@JoinColumn(name="user_id")})
//	@JsonIgnore
//	private Set<User> enrollusers;
//	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="AppointmentInviteUser",
//		joinColumns={@JoinColumn(name="appointment_id")},
//		inverseJoinColumns={@JoinColumn(name="user_id")})
//	@JsonIgnore
//	private Set<User> inviteusers;
	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="appointment")
	@JsonIgnore
	private Set<UserAppointmentRelation> userAppointmentRelations;
	
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
/*
	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
*/
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserAppointmentRelation> getUserAppointmentRelations() {
		return userAppointmentRelations;
	}

	public void setUserAppointmentRelations(Set<UserAppointmentRelation> userAppointmentRelations) {
		this.userAppointmentRelations = userAppointmentRelations;
	}

	
	public Set<User> findUsersByUserAppointmentRelations(){
		Set<User> users = new HashSet<User>();
		for (UserAppointmentRelation r : userAppointmentRelations) {
			users.add(r.getUser());
		}
		return users;
		
	}


//	public Set<User> getEnrollusers() {
//		return enrollusers;
//	}
//
//	public void setEnrollusers(Set<User> enrollusers) {
//		this.enrollusers = enrollusers;
//	}

//	public Set<User> getInviteusers() {
//		return inviteusers;
//	}
//
//	public void setInviteusers(Set<User> inviteusers) {
//		this.inviteusers = inviteusers;
//	}   
//	
	
	
}
