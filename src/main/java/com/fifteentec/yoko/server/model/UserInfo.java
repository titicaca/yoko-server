package com.fifteentec.yoko.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserInfo")
public class UserInfo extends BaseModel{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(cascade=CascadeType.ALL,mappedBy="UserInfo")
	private User user;
	
//	private Long user_id;
	@Column(name="sex")
	private int sex; 
	@Column(name="email")
    private String email;
	@Column(name="qq")
    private String qq;
	@Column(name="wechat")
    private String wechat;
	@Column(name="weibo")
    private String weibo;
	@Column(name="picturelink")
    private String picturelink;
    
    public UserInfo(){
    	
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Long getUser_id() {
//		return user_id;
//	}
//
//	public void setUser_id(Long user_id) {
//		this.user_id = user_id;
//	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User u){
		this.user = u;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getPicturelink() {
		return picturelink;
	}

	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}

}
