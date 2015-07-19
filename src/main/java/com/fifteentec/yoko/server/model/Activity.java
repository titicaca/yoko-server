package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Activity")
public class Activity extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="name")
	private String name;
	@ManyToOne
	private ActivityGroup activitygroup;
//	private  activitygroup_id;
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
	
	public ActivityGroup getActivityGroup(){
		return activitygroup;
	}
	
	public void setActivityGroup(ActivityGroup a){
		this.activitygroup = a;
	}
//	public int getActivitygroup_id() {
//		return activitygroup_id;
//	}
//
//	public void setActivitygroup_id(int activitygroup_id) {
//		this.activitygroup_id = activitygroup_id;
//	}

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
