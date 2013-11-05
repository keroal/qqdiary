package com.locusdiary.module;

import javax.swing.ImageIcon;


abstract public class GeneralAttachment {

	public static final String resouceDir="Media Resource\\";
	private String id;
	private String diaryId;
	private String type;
	private String path;
	private AttachmentStatus status;//¸½¼þ×´Ì¬±êÊ¶
	
	public GeneralAttachment(GeneralDiary diary){
		this.diaryId = diary.getId();
		this.id = TimeHelper.getInstance().createRandomID() + diary.getId();
		this.status = AttachmentStatus.NEW;
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
	
	public static String genAttachmentID(){
		return "";
	}
	
	public AttachmentStatus getStatus() {
		return status;
	}

	public void setStatus(AttachmentStatus status) {
		this.status = status;
	}
	
	public static enum AttachmentStatus{
		EXIST,
		NEW,
		DELETE
	}

	abstract public void showAttachmentContent();
	
	abstract public ImageIcon getAttachmentIcon();
	
	abstract public String generateDestPath();
	

}
