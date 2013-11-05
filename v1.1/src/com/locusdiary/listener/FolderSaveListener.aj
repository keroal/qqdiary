package com.locusdiary.listener;

import com.locusdiary.module.DiaryFolder;

public aspect FolderSaveListener {
	pointcut captureFolderSaveOperation(DiaryFolder folder) : 
		call(boolean com.locusdiary.dao.DiaryDao.saveFolder(DiaryFolder)) 
		&& args(folder);
    
    //异常对象初始化 Advice
    before(DiaryFolder folder) : captureFolderSaveOperation(folder) {
    	String content = "";
    	if (!folder.isStatus()) {
    		content = String.format("[修改分类] [用户]%s   ID：%s  信息：%s  %s\r\n", folder.getUserId(), folder.getId(),folder.getName(), folder.getDescribe());	
		}else {
			content = String.format("[新建分类] [用户]%s   ID：%s  信息：%s  %s\r\n", folder.getUserId(), folder.getId(),folder.getName(), folder.getDescribe());	
		}
    	FileLogger.getInstance().logFolder(content);
    }
}
