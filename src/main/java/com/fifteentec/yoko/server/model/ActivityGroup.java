package com.fifteentec.yoko.server.model;


public class ActivityGroup extends BaseModel{
	private int id;
	private String name;
	private int type;
	private int sponsor_id;
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
    
	public int getSponsor_id() {
		return sponsor_id;
	}

	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
