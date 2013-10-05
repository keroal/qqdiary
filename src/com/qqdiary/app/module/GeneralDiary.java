package com.qqdiary.app.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralDiary implements Serializable{
	private String id;
	private DiaryFolder folder;
	private String title;
	private String date;
	private String content;
	private String weather;
	private boolean hasAttachement;
	private List<DiaryAttachment> attachmentList;
	
	public GeneralDiary() {
		this.setHasAttachement(false);
		this.attachmentList = new ArrayList<DiaryAttachment>();
	}
	
	
	public GeneralDiary(String id, DiaryFolder folder, String title, String date, String weather, String content, boolean hasAttach) {
		this.id = id;
		this.setFolder(folder);
		this.title = title;
		this.date = date;
		this.weather = weather;
		this.content = content;
		this.hasAttachement = hasAttach;
		this.attachmentList = new ArrayList<DiaryAttachment>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<DiaryAttachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<DiaryAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	public void addAttachment(DiaryAttachment attachment){
		this.attachmentList.add(attachment);
	}
	
	public void delAttachment(DiaryAttachment attachment) {
		this.attachmentList.remove(attachment);
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}

	
	public String tosString() {
		return title;
	}





	public boolean isHasAttachement() {
		return hasAttachement;
	}


	public void setHasAttachement(boolean hasAttachement) {
		this.hasAttachement = hasAttachement;
	}


	public DiaryFolder getFolder() {
		return folder;
	}


	public void setFolder(DiaryFolder folder) {
		this.folder = folder;
	}
	
	public List<DiaryAttachment> getAttachments(){
		return null;
	}
	
	
}
