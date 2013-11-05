package com.locusdiary.listener;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralDiary;

public aspect FolderDeleteListener {
	pointcut captureFolderDeleteOperation(DiaryFolder folder) : 
		call(void com.locusdiary.dao.DiaryDao.deleteFolder(DiaryFolder)) 
		&& args(folder);
    
    //�쳣�����ʼ�� Advice
    before(DiaryFolder folder) : captureFolderDeleteOperation(folder) {
    	String content = "";
    	content = String.format("[ɾ������] [�û�]%s   ID��%s  ���ƣ�%s\r\n", folder.getUserId(), folder.getId(),folder.getName());	
		FileLogger.getInstance().logFolder(content);
    }
}
