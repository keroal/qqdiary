package com.locusdiary.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.locusdiary.dao.DiaryDao;
import com.locusdiary.dao.DiaryDaoImp;
import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralAttachment;
import com.locusdiary.module.GeneralComment;
import com.locusdiary.module.GeneralDiary;
import com.locusdiary.module.SearchParameters;
import com.locusdiary.module.User;

public class Manager implements DiaryManager, FolderManager, UserManager {
	DiaryDao dao = new DiaryDaoImp();

	public DiaryDao getDao() {
		return dao;
	}

	public void setDao(DiaryDao dao) {
		this.dao = dao;
	}
	
	private void copyFile(File sourceFile,File destFile) {
		try {		
		    FileInputStream inputStream=new FileInputStream(sourceFile);
		    BufferedInputStream inPutBuffer=new BufferedInputStream(inputStream);
		
		    FileOutputStream outputStream=new FileOutputStream(destFile);
		    BufferedOutputStream outputBuffer=new BufferedOutputStream(outputStream);
		
		    byte[] buffer=new byte[1024*5];
		    int len=0;
		    while ((len=inPutBuffer.read(buffer))!=-1) {
			    outputBuffer.write(buffer, 0, len);
		    }
		
		    outputBuffer.flush();
		
            inPutBuffer.close();
            outputBuffer.close();
            inputStream.close();
            outputStream.close();	
		}catch (Exception e) { 
	        e.printStackTrace(); 
	        
	    } 
	}
	
	private boolean sendDiaryToServer(GeneralDiary diary) {
		if (diary.isHasAttachement()) {
			List<GeneralAttachment> attachmentList=diary.getAttachmentList();
			java.util.Iterator<GeneralAttachment> iteratorOfAttach=attachmentList.iterator();
			
			while (iteratorOfAttach.hasNext()) {
				GeneralAttachment attachment=iteratorOfAttach.next();
				
				switch (attachment.getStatus()) {
				case NEW:
				{
					String sourceAttachPath=attachment.getPath();
					File sourceFile=new File(sourceAttachPath);
					
					File destFile=new File(attachment.generateDestPath());
					copyFile(sourceFile,destFile);
					
					attachment.setPath(attachment.generateDestPath());
					break;
				}
					
				case DELETE:
				{
					String sourcePath = attachment.getPath();
					File sourceFile = new File(sourcePath);
					sourceFile.delete();
					
					//iteratorOfAttach.remove();
					break;
				}

				default:
					break;
				}		
			}			
		}
		
		recvDiaryFromClient(diary);
		return true;		
	}
	
	private boolean recvDiaryFromClient(GeneralDiary diary){
		dao.saveDiary(diary);
		return true;
	}

	@Override
	public void saveDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		sendDiaryToServer(diary);
	}
	
	@Override
	public boolean saveDiaryFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		return dao.saveFolder(folder);
	}

	@Override
	public List<DiaryFolder> getDiaryFolderList(User user) {
		// TODO Auto-generated method stub
		return dao.getFolderList(user);
	}

	@Override
	public List<GeneralDiary> getDiaries(DiaryFolder folder) {
		// TODO Auto-generated method stub
		return dao.getDiaries(folder);
	}

	@Override
	public List<GeneralDiary> getDiaries(SearchParameters parameters) {
		// TODO Auto-generated method stub
		return dao.getDiaries(parameters);
	}

	@Override
	public void deleteFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		deleteDiaries(dao.getDiaries(folder));
		dao.deleteFolder(folder);
	}
	
	@Override
	public void deleteDiary(GeneralDiary diary) {
		// TODO Auto-generated method stub
		List<GeneralDiary> diaries = new ArrayList<GeneralDiary>();
		diaries.add(diary);
		deleteDiaries(diaries);	
	}
	
	
	
	private void deleteDiaries(List<GeneralDiary> diaries) {
		// TODO Auto-generated method stub
		java.util.Iterator<GeneralDiary> iteratorOfDiarys=diaries.iterator();
		
		while (iteratorOfDiarys.hasNext()) {
			GeneralDiary diary=iteratorOfDiarys.next();
			
			if (!diary.getAttachmentList().isEmpty()) {
				List<GeneralAttachment> attachMentList=diary.getAttachmentList();
				java.util.Iterator<GeneralAttachment> iteratorOfAttach=attachMentList.iterator();
				
				while (iteratorOfAttach.hasNext()) {
					GeneralAttachment attachment=iteratorOfAttach.next();
					File file=new File(attachment.getPath());
					file.delete();			
				}		
			}
		}
		
     	dao.deleteDiaries(diaries);
	}


	@Override
	public User userVerify(User user) {
		// TODO Auto-generated method stub
		return dao.userVerify(user);
	}

	@Override
	public User userRegister(User user) {
		// TODO Auto-generated method stub	
		return dao.userRegister(user);
	}

	@Override
	public void defaultShareFunction() {
		// TODO Auto-generated method stub	
	}
	
	public List<GeneralAttachment> getDiaryAttachments(GeneralDiary diary){
		return dao.getDiaryAttachments(diary);
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return dao.getUser(user);
	}

	@Override
	public List<GeneralComment> getDiaryComments(GeneralDiary diary) {
		// TODO Auto-generated method stub
		return dao.getCommentList(diary);
	}

	@Override
	public List<User> getFriends(User user) {
		// TODO Auto-generated method stub
		return dao.getFriends(user);
	}

	@Override
	public List<GeneralDiary> getDiaries(User user) {
		// TODO Auto-generated method stub
		return dao.getDiaries(user);
	}

	@Override
	public void addComment(GeneralComment comment) {
		// TODO Auto-generated method stub
		dao.addComment(comment);
		
	}

	@Override
	public void delComment(GeneralComment comment) {
		// TODO Auto-generated method stub
		dao.delComment(comment);
	}

	@Override
	public boolean addFriend(User user, User friendUser) {
		// TODO Auto-generated method stub
		return dao.addFriend(user, friendUser);
	}

	@Override
	public void delFriend(User user, User friendUser) {
		// TODO Auto-generated method stub
		dao.delFriend(user, friendUser);
		
	}

}
