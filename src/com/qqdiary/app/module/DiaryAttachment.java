package com.qqdiary.app.module;

import java.io.File;

public class DiaryAttachment {
	
	private String id;
	private String diaryId;
	private String type;
	private String path;
	
	public DiaryAttachment(String id, String diaryId, String type, String path){
		this.id = id;
		this.diaryId = diaryId;
		this.type = type;
		this.path = path;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiaryId() {
		return diaryId;
	}
	public void setDiaryId(String diaryId) {
		this.diaryId = diaryId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
