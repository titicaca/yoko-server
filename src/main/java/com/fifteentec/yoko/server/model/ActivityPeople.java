package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ActivityPeople")
public class ActivityPeople {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="activity_id")
	private int activity_id;
	@Column(name="peopleenroll")
	private int peopleenroll;
	
	public ActivityPeople(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public int getPeopleenroll() {
		return peopleenroll;
	}

	public void setPeopleenroll(int peopleenroll) {
		this.peopleenroll = peopleenroll;
	}

	
}
