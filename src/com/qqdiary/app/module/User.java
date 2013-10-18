package com.qqdiary.app.module;


/**
 * 用户信息类
 * @author Administrator
 *
 */
public class User {
	private String username = "";
	private String password = "";
	private String sex = "";
	private String birthday = "";
	private String loaction = "";
	
	public User() {
		
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getLoaction() {
		return loaction;
	}

	public void setLoaction(String loaction) {
		this.loaction = loaction;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
