package com.fifteentec.yoko.server.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="UserFriendRelation")
public class UserFriendRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="friend_id")
	private User friend;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="TagUserFriendRelation",
	joinColumns={@JoinColumn(name="user_friend_relation_id")},
	inverseJoinColumns={@JoinColumn(name="tag_id")})
	private Set<Tag> tags;
	
	@Column(name="status",columnDefinition = "INT default 0")
	private int status;
	
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdtime")
	private Date createdtime = new Date();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	
	
	
}
