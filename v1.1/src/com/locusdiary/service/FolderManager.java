package com.locusdiary.service;

import java.util.List;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.User;

public interface FolderManager {
	boolean saveDiaryFolder(DiaryFolder folder);
	void deleteFolder(DiaryFolder folder); 
	List<DiaryFolder> getDiaryFolderList(User user);
}
