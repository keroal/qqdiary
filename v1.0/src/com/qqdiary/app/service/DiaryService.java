package com.qqdiary.app.service;

import java.util.List;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.SearchParameters;

/**
 * 基本数据操作服务接口定义
 * @author Administrator
 *
 */
public interface DiaryService {
	void saveDiaryFolder(DiaryFolder folder);
	void saveDiary(GeneralDiary diary);
	
	List<DiaryFolder> getDiaryFolderList();
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<GeneralDiary> getDiaries(SearchParameters parameters);
	List<DiaryAttachment> getDiaryAttachments(GeneralDiary diary);
	
	void deleteDiary(GeneralDiary diary);
	void deleteDiarys(List<GeneralDiary> diaries);
	void deleteFolder(DiaryFolder folder); 
}
