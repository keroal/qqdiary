package com.locusdiary.module;

import java.io.Serializable;
import java.util.List;


public class GeneralDiary implements Serializable{
	/**日记基本属性*/
	private String id;
	private String folderid;
	private String userid;
	private String title;
	private String buildTime;
	private String modifyTime;
	private String content;
	private String weather;
	private boolean hasAttachement;
	private boolean hasPublic;
	private boolean hasComment;
	private boolean status;
	private List<GeneralAttachment> attachmentList;
	private List<GeneralComment> commentList;
	
	public GeneralDiary() {
		userid = SessionManager.getInstance().getUser().getUserid();
		id = TimeHelper.getInstance().createRandomID() + userid;
		status = true;
		setHasAttachement(false);
		setHasComment(false);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFolderid() {
		return folderid;
	}

	public void setFolderid(String folderid) {
		this.folderid = folderid;
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
	public List<GeneralAttachment> getAttachmentList() {
		if(attachmentList == null){
			attachmentList = SessionManager.getInstance().getService().getDiaryAttachments(this);
		}
		return attachmentList;
	}
	public void setAttachmentList(List<GeneralAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	
	public void addAttachment(GeneralAttachment attachment){
		this.attachmentList.add(attachment);
	}
	
	public void delAttachment(GeneralAttachment attachment) {
		this.attachmentList.remove(attachment);
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}

	public boolean isHasAttachement() {
		return hasAttachement;
	}


	public void setHasAttachement(boolean hasAttachement) {
		this.hasAttachement = hasAttachement;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public List<GeneralComment> getCommentList() {
		if (commentList == null) {
			commentList = SessionManager.getInstance().getService().getDiaryComments(this);
		}
		return commentList;
	}

	public void setCommentList(List<GeneralComment> commentList) {
		this.commentList = commentList;
	}

	public boolean isHasComment() {
		return hasComment;
	}

	public void setHasComment(boolean hasComment) {
		this.hasComment = hasComment;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean isHasPublic() {
		return hasPublic;
	}

	public void setHasPublic(boolean hasPublic) {
		this.hasPublic = hasPublic;
	}
	
}
