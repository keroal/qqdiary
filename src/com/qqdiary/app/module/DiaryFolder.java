package com.qqdiary.app.module;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiaryFolder implements Serializable {
	private String id;
	private String name;
	private String describe;
	private List<GeneralDiary> diaryList;
	
	public DiaryFolder(){
		List<GeneralDiary> diaryList = new ArrayList<GeneralDiary>();
	}
	
	public DiaryFolder(String name, String describe){
		this.name = name;
		this.describe = describe;
		List<GeneralDiary> diaryList = new ArrayList<GeneralDiary>();
	}
	
	public DiaryFolder(String id, String name, String describe){
		this.id = id;
		this.name = name;
		this.describe = describe;
		List<GeneralDiary> diaryList = new ArrayList<GeneralDiary>();
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
	
}	
