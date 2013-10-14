package com.qqdiary.app.dao;

import java.util.List;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.SearchParameters;

/**
 * 数据操作层接口
 * @author Administrator
 *
 */
public interface DiaryDao {
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<DiaryAttachment> getDiaryAttachments(GeneralDiary diary);
	List<GeneralDiary> getDiaries(SearchParameters parameters);
	
	List<DiaryFolder> getFolderList();
	
	void saveDiary(GeneralDiary diary);
	void saveFolder(DiaryFolder folder);
	
	void deleteFolder(DiaryFolder folder);
	void deleteDiaries(List<GeneralDiary> diaries);
}
