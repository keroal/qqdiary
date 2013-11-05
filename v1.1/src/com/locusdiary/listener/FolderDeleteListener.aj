package com.locusdiary.listener;

import com.locusdiary.module.DiaryFolder;
import com.locusdiary.module.GeneralDiary;

public aspect FolderDeleteListener {
	pointcut captureFolderDeleteOperation(DiaryFolder folder) : 
		call(void com.locusdiary.dao.DiaryDao.deleteFolder(DiaryFolder)) 
		&& args(folder);
    
    //异常对象初始化 Advice
    before(DiaryFolder folder) : captureFolderDeleteOperation(folder) {
    	String content = "";
    	content = String.format("[删除分类] [用户]%s   ID：%s  名称：%s\r\n", folder.getUserId(), folder.getId(),folder.getName());	
		FileLogger.getInstance().logFolder(content);
    }
}
