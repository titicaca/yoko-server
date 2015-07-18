package com.fifteentec.yoko.server.model;

public class EnrollGroup extends BaseModel{
	private int user_id;
	private int activitygroup_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getActivitygroup_id() {
		return activitygroup_id;
	}

	public void setActivitygroup_id(int activitygroup_id) {
		this.activitygroup_id = activitygroup_id;
	}
	
}
