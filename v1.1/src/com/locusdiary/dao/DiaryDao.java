package com.locusdiary.dao;

import java.util.List;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SearchParameters;
import com.locusdiary.module.User;

/**
 * 数据操作层接口
 * @author Administrator
 *
 */
public interface DiaryDao {

	List<GeneralDiary> getDiaries(User user);
	List<GeneralDiary> getDiaries(DiaryFolder folder);
	List<GeneralAttachment> getDiaryAttachments(GeneralDiary diary);
	List<GeneralDiary> getDiaries(SearchParameters parameters);
	void saveDiary(GeneralDiary diary);
	void deleteDiaries(List<GeneralDiary> diaries);
	
	
	List<DiaryFolder> getFolderList(User user);
	void deleteFolder(DiaryFolder folder);
	boolean saveFolder(DiaryFolder folder);

	List<User>getFriends(User user);
	boolean addFriend(User user,User friendUser);
	void delFriend(User user,User friendUser);

	User userRegister(User user);
	User userVerify(User user);
	User getUser(User user);

	List<GeneralComment>getCommentList(GeneralDiary diary);
	void addComment(GeneralComment comment);
	void delComment(GeneralComment comment);
}
