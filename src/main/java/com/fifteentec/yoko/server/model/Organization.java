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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Organization")
public class Organization extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="type")
	private int type;

	@Column(name="picturelink")
	private String picturelink;
	
	@Column(name="introduction")
	private String introduction;
	
	@Column(name="mobile",unique=true)
	private String mobile;
		
	@Column(name="password")
	private String password;

	@Column(name="realname")
	private String realname;
	
	@Column(name="card",unique=true)
	private String card;
	
	@Column(name="address")
	private String address;
	
	@Column(name="photolink")
	private String photolink;
	
	
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdtime")
	private Date createdtime = new Date();
	
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;	
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="organization")
	@JsonIgnore
	private Set<Activity> activities;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="UserEnrollOrganization",
		joinColumns={@JoinColumn(name="organization_id")},
		inverseJoinColumns={@JoinColumn(name="user_id")})
	@JsonIgnore
	private Set<User> users;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name=name;
    }
    
	public int getType(){
    	return type;
    }
    
    public void setType(int type){
    	this.type=type;
    }
    
	public String getPicturelink() {
		return picturelink;
	}

	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotolink() {
		return photolink;
	}

	public void setPhotolink(String photolink) {
		this.photolink = photolink;
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

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}   
	
	
	

}
