package org.weibo.entity;

import java.util.Set;

public class User {
	private String id;
	private String username;
	private String password;
	private Integer followerC;
	private Integer followingC;
	private Set<Post> posts;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	public Integer getFollowerC() {
		return followerC;
	}
	public void setFollowerC(Integer followerC) {
		this.followerC = followerC;
	}
	public Integer getFollowingC() {
		return followingC;
	}
	public void setFollowingC(Integer followingC) {
		this.followingC = followingC;
	}
	
}
