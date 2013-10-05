package com.qqdiary.app.dao;

import java.util.List;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;

public interface DiaryDao {
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<GeneralDiary> getDiaries(String key);
	List<GeneralDiary> getDiaries(String sDate, String eDate);
	
	List<DiaryFolder> getFolderList();
	
	void saveDiary(GeneralDiary diary);
	void saveFolder(DiaryFolder folder);
	
	void deleteFolder(DiaryFolder folder);
	void deleteDiaries(List<GeneralDiary> diaries);
}
