package com.qqdiary.app.module;

import java.io.File;

/**
 * 日记附件数据类
 * @author Administrator
 *
 */
public class DiaryAttachment {

	/**附件基本属性*/
	private String id;
	private String diaryId;
	private String type;
	private String path;
	private AttachmentStatus status;//附件状态标识
	
	public DiaryAttachment(){
		this.id = IDFactiory.getInstance().createAttachmentID();
		this.status = AttachmentStatus.NEW;
	}
	
	public DiaryAttachment(String id, String diaryId, String type, String path){
		this.id = id;
		this.diaryId = diaryId;
		this.type = type;
		this.path = path;
		this.status = AttachmentStatus.EXIST;
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

}
