package com.fifteentec.yoko.server.model;

import java.util.Set;

public class FriendTags{
	private User user;
	private Set<Tag> tags;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	

}
