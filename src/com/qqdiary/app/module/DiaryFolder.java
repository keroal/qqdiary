package com.qqdiary.app.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 日记分数据类
 * @author Administrator
 *
 */
public class DiaryFolder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**日记分类基本属性*/
	private String id;
	private String name;
	private String describe;
	private boolean isNew;//日记状态标识
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
