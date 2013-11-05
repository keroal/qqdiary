package com.qqdiary.app.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * �ռǷ�������
 * @author Administrator
 *
 */
public class DiaryFolder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**�ռǷ����������*/
	private String id;
	private String name;
	private String describe;
	private boolean isNew;//�ռ�״̬��ʶ
	private List<GeneralDiary> diariesList;

	public DiaryFolder(){
		this.isNew = true;
		this.id = IDFactiory.getInstance().createFolderID();
		diariesList = new ArrayList<GeneralDiary>();
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

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	public List<GeneralDiary> getDiariesList() {
		return diariesList;
	}

	public void setDiariesList(List<GeneralDiary> diariesList) {
		this.diariesList = diariesList;
	}
	
}	
