package com.locusdiary.module;

import java.util.ArrayList;
import java.util.List;

public class DiaryFolder {
	private String id;
	private String userId;
	private String name;
	private String describe;
	private boolean status;//日记状态标识

	public DiaryFolder(){
		this.setStatus(true);
		this.id = TimeHelper.getInstance().createRandomID() + SessionManager.getInstance().getUser().getUserid() ;
	}
	
	public DiaryFolder(String id, String name, String describe) {
		this.id = id;
		this.name = name;
		this.describe = describe;
		this.setStatus(false);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public String toString() {
		return name;
	}
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
