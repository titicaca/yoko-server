package com.fifteentec.yoko.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Sponsor")
public class Sponsor extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="name", nullable=false, unique=true)
	private String name;
	@Column(name="card", nullable=false, unique=true)
	private String card;
	@Column(name="mobile", nullable=false, unique=true)
	private String mobile;
	@Column(name="address")
	private String Address;
	@Column(name="picturelink")
	private String picturelink;
	
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
	
	public String getCard() {
		return card;
	}
	
	public void setCard(String card) {
		this.card = card;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String address) {
		Address = address;
	}
	
	public String getPicturelink() {
		return picturelink;
	}
	
	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}
	
}
