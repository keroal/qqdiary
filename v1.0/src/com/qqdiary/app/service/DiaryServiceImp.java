package com.qqdiary.app.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


import com.qqdiary.app.dao.DiaryDao;
import com.qqdiary.app.dao.DiaryDaoImp;
import com.qqdiary.app.module.DiaryAttachment;
import com.qqdiary.app.module.DiaryFolder;
import com.qqdiary.app.module.GeneralDiary;
import com.qqdiary.app.module.SearchParameters;

/**
 * 服务层操作类
 * @author Administrator
 *
 */
public class DiaryServiceImp implements DiaryService , DiaryShare, UserVerify{
	public static final String resouceDir="Media Resource\\";
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
		}
	    catch (Exception e) { 
	        System.out.println("复制单个文件操作出错"); 
	        e.printStackTrace(); 
	        } 
	}
	
	private boolean sendDiaryToServer(GeneralDiary diary) {
		if (diary.isHasAttachement()) {
			List<DiaryAttachment> attachmentList=diary.getAttachmentList();
			java.util.Iterator<DiaryAttachment> iteratorOfAttach=attachmentList.iterator();
			
			while (iteratorOfAttach.hasNext()) {
				DiaryAttachment attachment=iteratorOfAttach.next();
				
				switch (attachment.getStatus()) {
				case NEW:
				{
					String sourceAttachPath=attachment.getPath();
					File sourceFile=new File(sourceAttachPath);
					String destPaht = resouceDir+attachment.getId();
					if (attachment.getType().contains("图片")) {
						destPaht += ".png";
					}else if(attachment.getType().contains("语音")) {
						destPaht += ".wav";
					}
					File destFile=new File(destPaht);
					copyFile(sourceFile,destFile);
					
					attachment.setPath(destPaht);
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
	public List<GeneralDiary> getDiaries(SearchParameters parameters) {
		// TODO Auto-generated method stub
		return dao.getDiaries(parameters);
	}

	@Override
	public void deleteFolder(DiaryFolder folder) {
		// TODO Auto-generated method stub
		deleteDiarys(dao.getDiaries(folder));
		
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
		java.util.Iterator<GeneralDiary> iteratorOfDiarys=diaries.iterator();
		
		while (iteratorOfDiarys.hasNext()) {
			GeneralDiary diary=iteratorOfDiarys.next();
			
			if (diary.isHasAttachement()) {
				List<DiaryAttachment> attachMentList=diary.getAttachmentList();
				java.util.Iterator<DiaryAttachment> iteratorOfAttach=attachMentList.iterator();
				
				while (iteratorOfAttach.hasNext()) {
					DiaryAttachment attachment=iteratorOfAttach.next();
					File file=new File(attachment.getPath());
					file.delete();			
				}		
			}					
		}
		
     	dao.deleteDiaries(diaries);
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
	
	public List<DiaryAttachment> getDiaryAttachments(GeneralDiary diary){
		return dao.getDiaryAttachments(diary);
	}
	
	public static void main(String[] args){
	
		
	}

}
