package com.fifteentec.yoko.server.model;

public class PushInfo {

	private Long userid;
	private Long channelid;
	private int devicetype;		//1 for web, 2 for pc, 
    //3 for android, 4 for ios, 5 for wp.
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getChannelid() {
		return channelid;
	}
	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}
	public int getDevicetype() {
		return devicetype;
	}
	public void setDevicetype(int devicetype) {
		this.devicetype = devicetype;
	}
	
	
}
