package com.locusdiary.module;

public class AttachmentFileFactory {

	private static AttachmentFileFactory attachmentFileFactory = null;
	
	private AttachmentFileFactory() {
		
	}
	
	public static AttachmentFileFactory getInstance() {
		if (attachmentFileFactory == null) {
			return new AttachmentFileFactory();
		}
		
		return attachmentFileFactory;
	}
	
	public GeneralAttachment createAttachment(GeneralDiary diary, String type) {
		GeneralAttachment attachment = null;
		switch (type) {
		case "ͼƬ":
			attachment = new PictureAttachment(diary);
			break;
		case "����":
			attachment = new VoiceAttachment(diary);
			break;
		default:
			break;
		}
		
		return attachment;
	}
}
