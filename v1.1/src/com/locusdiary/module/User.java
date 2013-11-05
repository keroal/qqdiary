package com.locusdiary.module;

public class User {
	private String username = "";
	private String password = "";
	private String email = "";
	private String userid = "";
	
	public User() {
		
	}
	
	public User(String id, String email, String username) {
		this.userid = id;
		this.email = email;
		this.username = username;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
