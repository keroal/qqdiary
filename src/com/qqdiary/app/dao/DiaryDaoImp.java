package com.qqdiary.app.dao;

import java.util.List;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;

public class DiaryDaoImp implements DiaryDao {
	private final String QUERY_ALL_DIARY_FOLDERS = "";
	private final String QUERY_ALL_DIARY_BY_FOLDER = "";
	private final String QUERY_ALL_DIARY_BY_KEY = "";
	private final String QUERY_ALL_DIARY_BY_TIME = "";
	private final String INSERT_DIARY = "";
	private final String UPDATE_DIARY = "upate diarytable set name=?, describe=? where id=?";
	private final String INSERT_FOLDER = "insert into foldertable (name,describe) values (?,?)";
	private final String UPDATE_FOLDER = "upate foldertable set name=?, describe=? where id=?";
	private final String DELETE_DIARY_MAIN = "delete from diarytable where did in (?)";
	private final String DELETE_DIARY_ATTACHMENT = "delete from attachmenttable where did in (?)";
	private final String DELETE_FOLDER = "delete from foldertable where fid in (?)";
	

	@Override
	public List<GeneralDiary> getDiaries(DiaryFolder folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeneralDiary> getDiaries(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GeneralDiary> getDiaries(String sDate, String eDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DiaryFolder> getFolderList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void saveDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		if (diary.getId() == "") {
			InsertDiaryAttribute(diary);
		} else {
			updateDiaryAttribute(diary);
		}

		saveDiaryAttachment(diary);
	}
	

	
	@Override
	public void deleteDiaries(List<GeneralDiary> diary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void saveFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		if (folder.getId() == "") {
			InsertDiaryFolder(folder);
		} else {
			updateDiaryFolder(folder);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void InsertDiaryFolder(DiaryFolder folder){
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateDiaryFolder(DiaryFolder folder){
		
	}
	
	@SuppressWarnings("unchecked")
	private void InsertDiaryAttribute(GeneralDiary diary){
		
	}
	
	@SuppressWarnings("unchecked")
	private void updateDiaryAttribute(GeneralDiary diary){
		
	}
	
	@SuppressWarnings("unchecked")
	private void saveDiaryAttachment(GeneralDiary diary){
		deleteAllDiaryAttachments(diary);
		for (DiaryAttachment attachment : diary.getAttachmentList()) {
			insertDiaryAttachment(attachment);
		}
	}

	@SuppressWarnings("unchecked")
	private List<DiaryAttachment> getDiaryAttachments(GeneralDiary diary){
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void insertDiaryAttachment(DiaryAttachment attachment){
		
	}
	
	@SuppressWarnings("unchecked")
	private void deleteAllDiaryAttachments(GeneralDiary diary){
		
	}
	
}
