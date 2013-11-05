package com.locusdiary.service;

import java.util.List;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SearchParameters;
import com.locusdiary.module.User;

public interface DiaryManager {
	void saveDiary(GeneralDiary diary);
	void deleteDiary(GeneralDiary diary);
	List<GeneralDiary> getDiaries(User user);
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<GeneralDiary> getDiaries(SearchParameters parameters);
	List<GeneralAttachment> getDiaryAttachments(GeneralDiary diary);
	
	List<GeneralComment> getDiaryComments(GeneralDiary diary);
	void addComment(GeneralComment comment);
	void delComment(GeneralComment comment);
	
	void defaultShareFunction();
}
