package com.locusdiary.module;

import com.locusdiary.service.Manager;

public class SessionManager {
	private Manager service;
	private User user;
	
	private static SessionManager sessionManager = null;
	
	private SessionManager() {
		service = new Manager();
	}
	
	public static SessionManager getInstance() {
		if(sessionManager == null){
			sessionManager = new SessionManager();
			return sessionManager;
		}
		
		return sessionManager;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public Manager getService() {
		return service;
	}
}
