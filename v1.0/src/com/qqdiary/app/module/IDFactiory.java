package com.qqdiary.app.module;

import java.util.Calendar;

/**
 * ID�����ࣨ����ģʽ��
 * @author Administrator
 *
 */
public class IDFactiory {
	public static final String FOLDER_ID_PREFIX = "10";
	public static final String DIARY_ID_PREFIX = "20";
	public static final String ATTACHMENT_ID_PREFIX = "30";

	private static IDFactiory idFactiory = null;
	
	private IDFactiory() {
		
	}
	
	public static IDFactiory getInstance(){
		if(idFactiory == null){
			return new IDFactiory();
		}
		
		return idFactiory;
	}
	
	/**
	 * ����Ψһ�ռ�ID
	 * @return
	 */
	public String createDiaryID(){
		return DIARY_ID_PREFIX + getTime();
	}
	
	/**
	 * ����Ψһ����ID
	 * @return
	 */
	public String createFolderID() {
		return FOLDER_ID_PREFIX + getTime();
	}
	
	/**
	 * ����Ψһ����ID
	 * @return
	 */
	public String createAttachmentID() {
		return ATTACHMENT_ID_PREFIX + getTime();
	}
	
	/**
	 * ��������ΨһID
	 * @return
	 */
	public String createRandomID() {
		return getTime();
	}
	
	private String getTime() {
		Calendar c = Calendar.getInstance(); 
	    long millis = c.getTimeInMillis(); 
		return Long.toString(millis);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(IDFactiory.getInstance().createFolderID());
		
	}
	
	
}
