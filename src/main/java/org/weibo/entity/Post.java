package org.weibo.entity;

public class Post implements Comparable {
	private String id;
	private String content;
	private String time;
	private String username;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public int compareTo(Object o) {
		Post post  = (Post)o;
		
		return (Integer.parseInt(post.getId()) - Integer.parseInt(this.id));
	}

}
