package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ActivityGroup")
public class ActivityGroup extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="type")
	private int type;
	@ManyToOne
	private Sponsor sponsor;
//	private int sponsor_id;
	@Column(name="introduction")
	private String introduction;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
    
    public Sponsor getSponsor(){
    	return sponsor;
    }
    
    public void setSponsor(Sponsor s){
    	this.sponsor = s;
    }
    
//	public int getSponsor_id() {
//		return sponsor_id;
//	}
//
//	public void setSponsor_id(int sponsor_id) {
//		this.sponsor_id = sponsor_id;
//	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
