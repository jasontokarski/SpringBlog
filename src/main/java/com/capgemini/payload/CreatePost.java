package com.capgemini.payload;

import java.util.Date;

public class CreatePost {
	private String username;
	private String title;
	private String content;
	private Date postDate;
	public CreatePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreatePost(String username, String title, String content, Date postDate) {
		super();
		this.username = username;
		this.title = title;
		this.content = content;
		this.postDate = postDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
