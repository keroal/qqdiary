package com.qqdiary.app.service;

import java.util.List;

import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;

public interface DiaryService {
	void saveDiaryFolder(DiaryFolder folder);
	void  saveDiary(GeneralDiary diary);
	
	List<DiaryFolder> getDiaryFolderList();
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<GeneralDiary> getDiaries(String key);
	List<GeneralDiary> getDiaries(String sdate, String edate);
	
	void deleteDiary(GeneralDiary diary);
	void deleteDiarys(List<GeneralDiary> diaries);
	void deleteFolder(DiaryFolder folder); 

}
