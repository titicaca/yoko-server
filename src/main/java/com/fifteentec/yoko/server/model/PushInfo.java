package com.fifteentec.yoko.server.model;

public class PushInfo {

	private Long userid;
	private Long channelid;
	private String deviceinfo;
	
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
	public String getDeviceinfo() {
		return deviceinfo;
	}
	public void setDeviceinfo(String deviceinfo) {
		this.deviceinfo = deviceinfo;
	}
}
