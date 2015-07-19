package com.fifteentec.yoko.server.model;

//TODO use ORM to define many-to-many relationship

public class NewFriend extends BaseModel{
	private int id;
	private int user_id1;
	private int user_id2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUser_id1() {
		return user_id1;
	}

	public void setUser_id1(int user_id1) {
		this.user_id1 = user_id1;
	}

	public int getUser_id2() {
		return user_id2;
	}

	public void setUser_id2(int user_id2) {
		this.user_id2 = user_id2;
	}

}
