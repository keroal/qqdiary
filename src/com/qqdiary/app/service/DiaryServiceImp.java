package com.qqdiary.app.service;

import java.util.ArrayList;
import java.util.List;

import com.qqdiary.app.dao.DiaryDao;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;

public class DiaryServiceImp implements DiaryService , DiaryShare, UserVerify{
	DiaryDao dao;
	DiaryShare share;

	public DiaryDao getDao() {
		return dao;
	}

	public void setDao(DiaryDao dao) {
		this.dao = dao;
	}

	@Override
	public void saveDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		dao.saveDiary(diary);
	}
	
	@Override
	public void saveDiaryFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		dao.saveFolder(folder);
	}

	@Override
	public List<DiaryFolder> getDiaryFolderList() {
		// TODO Auto-generated method stub
		return dao.getFolderList();
	}

	@Override
	public List<GeneralDiary> getDiaries(DiaryFolder folder) {
		// TODO Auto-generated method stub
		return dao.getDiaries(folder);
	}

	@Override
	public List<GeneralDiary> getDiaries(String key) {
		// TODO Auto-generated method stub
		return dao.getDiaries(key);
	}

	@Override
	public List<GeneralDiary> getDiaries(String sdate, String edate) {
		// TODO Auto-generated method stub
		return dao.getDiaries(sdate, edate);
	}

	@Override
	public void deleteFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		dao.deleteFolder(folder);
	}
	
	@Override
	public void deleteDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		List<GeneralDiary> diaries = new ArrayList<GeneralDiary>();
		diaries.add(diary);
		deleteDiarys(diaries);
	}
	
	

	@Override
	public void deleteDiarys(List<GeneralDiary> diaries) {
		// TODO Auto-generated method stub
		for (GeneralDiary generalDiary : diaries) {
			deleteDiary(generalDiary);
		}
	}


	@Override
	public boolean userVerify(String name, String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void userRegister() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defaultShareFunction() {
		// TODO Auto-generated method stub
		
	}

}
