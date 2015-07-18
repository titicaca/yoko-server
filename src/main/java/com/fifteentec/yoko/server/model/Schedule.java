package com.fifteentec.yoko.server.model;

public class Schedule extends BaseModel{
	private int user_id;
	private String timebegin;
	private String timeend;
	private String location;
	private int type;
	private int remind;
	private int duplication;
	private String introduction;
	private int property;
	private String detaillink;
	
	public Schedule(){
		
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
