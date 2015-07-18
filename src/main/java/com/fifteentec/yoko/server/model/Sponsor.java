package com.fifteentec.yoko.server.model;

public class Sponsor extends BaseModel{
	private String name;
	private String card;
	private String mobile;
	private String Address;
	private String picturelink;
	
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
