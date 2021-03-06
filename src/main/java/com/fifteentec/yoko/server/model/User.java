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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/** 
 * BlueSun 
 * 15-07-15 
 * Version 1.0 
 **/ 

@Entity
@Table(name = "User")
public class User extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(name="nickname")
    private String nickname;
	
	@Column(name="mobile")
    private String mobile;
	
	@Column(name="sex")
	private int sex; 
	
	@Column(name="location")
	private String location;
	
	@Column(name="email")
    private String email;
	
	@Column(name="qq")
    private String qq;
	
	@Column(name="wechat")
    private String wechat;
	
	@Column(name="weibo")
    private String weibo;
	
	@Column(name="picturelink")
    private String picturelink;
	
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdtime")
	private Date createdtime = new Date();
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="logintime")
	private Date logintime = new Date();
	
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;	
	
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
//	@JsonIgnore
//	private Userinfo userinfo;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore
	private Set<Schedule> schedules;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore 
	private Set<Appointment> appointments ;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)  
	@JoinTable(name="UserCollectActivity",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="activity_id")})
	@JsonIgnore
	private Set<Activity> collectactivities;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)  
	@JoinTable(name="UserEnrollActivity",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="activity_id")})
	@JsonIgnore 
	private Set<Activity> enrollactivities;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="UserEnrollOrganization",
		joinColumns={@JoinColumn(name="user_id")},
		inverseJoinColumns={@JoinColumn(name="organization_id")})
	@JsonIgnore
	private Set<Organization> organizations;
	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="UserEnrollAppointment",
//		joinColumns={@JoinColumn(name="user_id")},
//		inverseJoinColumns={@JoinColumn(name="appointment_id")})
//	@JsonIgnore 
//	private Set<Appointment> enrollappointments;
	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="AppointmentInviteUser",
//		joinColumns={@JoinColumn(name="user_id")},
//		inverseJoinColumns={@JoinColumn(name="appointment_id")})
//	@JsonIgnore 
//	private Set<Appointment> inviteappointments;
	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="UserAddFriend")
//	@JsonIgnore 
//	private Set<User> addfriends;
	
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JsonIgnore 
//	private Set<User> friends;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore
	private Set<UserAppointmentRelation> userAppointmentRelations;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore
	private Set<UserFriendRelation> userFriendRelations;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="friend")
	@JsonIgnore
	private Set<UserFriendRelation> friendUserRelations;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore
	private Set<UserRequestFriend> userRequestFriends;
	
	@OneToMany(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY,mappedBy="friend")
	@JsonIgnore
	private Set<UserFriendRelation> friendUserRequest;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="user")
	@JsonIgnore
	private Set<Tag> tags;
	
	private int collectnumber;
	
	private int enrollnumber;
	
	private int friendnumber;
//	
//	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinTable(name="EnrollAppointment",
//		joinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")},
//		inverseJoinColumns={@JoinColumn(name="appointment_id",referencedColumnName="appointment_id")})
//	private Set<Appointment> appointments2;
	
    
    public User(){
    	
    }
    
    public User(String mobile, String password) {
		//this.mobile = mobile;
	//	this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getPicturelink() {
		return picturelink;
	}

	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	
	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
//	public Userinfo getUserinfo() {
//		return userinfo;
//	}
//
//	public void setUserinfo(Userinfo userinfo) {
//		this.userinfo = userinfo;
//	}

	public Set<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Set<Activity> getCollectactivities() {
		return collectactivities;
	}

	public void setCollectactivities(Set<Activity> collectactivities) {
		this.collectactivities = collectactivities;
	}

	public Set<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	public Set<Activity> getEnrollactivities() {
		return enrollactivities;
	}

	public void setEnrollactivities(Set<Activity> enrollactivities) {
		this.enrollactivities = enrollactivities;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Set<UserAppointmentRelation> getUserAppointmentRelations() {
		return userAppointmentRelations;
	}

	public void setUserAppointmentRelations(Set<UserAppointmentRelation> userAppointmentRelations) {
		this.userAppointmentRelations = userAppointmentRelations;
	}
	
	public Set<UserFriendRelation> getUserFriendRelations() {
		return userFriendRelations;
	}

	public void setUserFriendRelations(Set<UserFriendRelation> userFriendRelations) {
		this.userFriendRelations = userFriendRelations;
	}

	public Set<UserFriendRelation> getFriendUserRelations() {
		return friendUserRelations;
	}

	public void setFriendUserRelations(Set<UserFriendRelation> friendUserRelations) {
		this.friendUserRelations = friendUserRelations;
	}

	public Set<UserRequestFriend> getUserRequestFriends() {
		return userRequestFriends;
	}

	public void setUserRequestFriends(Set<UserRequestFriend> userRequestFriends) {
		this.userRequestFriends = userRequestFriends;
	}

	public Set<UserFriendRelation> getFriendUserRequest() {
		return friendUserRequest;
	}

	public void setFriendUserRequest(Set<UserFriendRelation> friendUserRequest) {
		this.friendUserRequest = friendUserRequest;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	

	public int getCollectnumber() {
		return collectnumber;
	}

	public void setCollectnumber(int collectnumber) {
		this.collectnumber = collectnumber;
	}

	public int getEnrollnumber() {
		return enrollnumber;
	}

	public void setEnrollnumber(int enrollnumber) {
		this.enrollnumber = enrollnumber;
	}

	public int getFriendnumber() {
		return friendnumber;
	}

	public void setFriendnumber(int friendnumber) {
		this.friendnumber = friendnumber;
	}

	public Set<Appointment> findAppointmentsByUserAppointmentRelations(){
		Set<Appointment> appointments = new HashSet<Appointment>();
		//Set<UserAppointmentRelation> userAppointmentRelations = getUserAppointmentRelations();
		for (UserAppointmentRelation userAppointmentRelation : userAppointmentRelations) {
			appointments.add(userAppointmentRelation.getAppointment());
		}
		return appointments;
		
	}
	
	public Set<User> findFriendsByUserRequestFriend(){
		Set<User> friends = new HashSet<User>();
		for (UserRequestFriend userRequestFriend : userRequestFriends) {
			friends.add(userRequestFriend.getFriend());
		}
		return friends;
	}
	
	public Set<User> findFriendsByUserFriendRelations(){
		Set<User> friends = new HashSet<User>();
		for (UserFriendRelation userFriendRelation : userFriendRelations) {
			friends.add(userFriendRelation.getFriend());
		}
		return friends;
	}
	
	
	
	
}
