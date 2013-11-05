package com.locusdiary.module;

public class GeneralComment {
	private String diaryId;
	private User publishUser;
	private String publishTime;
	private String content;
	
	public GeneralComment(){
		
	}
	
	public String getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}

	public User getPublishUser() {
		return publishUser;
	}

	public void setPublishUser(User publishUser) {
		this.publishUser = publishUser;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
