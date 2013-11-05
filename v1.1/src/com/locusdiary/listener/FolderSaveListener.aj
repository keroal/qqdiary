package com.locusdiary.listener;

import com.locusdiary.module.DiaryFolder;

public aspect FolderSaveListener {
	pointcut captureFolderSaveOperation(DiaryFolder folder) : 
		call(boolean com.locusdiary.dao.DiaryDao.saveFolder(DiaryFolder)) 
		&& args(folder);
    
    //�쳣�����ʼ�� Advice
    before(DiaryFolder folder) : captureFolderSaveOperation(folder) {
    	String content = "";
    	if (!folder.isStatus()) {
    		content = String.format("[�޸ķ���] [�û�]%s   ID��%s  ��Ϣ��%s  %s\r\n", folder.getUserId(), folder.getId(),folder.getName(), folder.getDescribe());	
		}else {
			content = String.format("[�½�����] [�û�]%s   ID��%s  ��Ϣ��%s  %s\r\n", folder.getUserId(), folder.getId(),folder.getName(), folder.getDescribe());	
		}
    	FileLogger.getInstance().logFolder(content);
    }
}
