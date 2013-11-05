package com.locusdiary.listener;

import com.locusdiary.module.GeneralDiary;

public aspect DiarySaveListener {
	pointcut captureDiarySaveOperation(GeneralDiary diary) : 
		call(void com.locusdiary.dao.DiaryDao.saveDiary(GeneralDiary)) 
		&& args(diary);
    
    //异常对象初始化 Advice
    before(GeneralDiary diary) : captureDiarySaveOperation(diary) {
    	String content = "";
    	if (!diary.isStatus()) {
    		content = String.format("[修改日记] [用户]%s   ID：%s  标题：%s\r\n", diary.getUserid(), diary.getId(),diary.getTitle());	
		}else {
			content = String.format("[新建日记] [用户]%s   ID：%s  标题：%s\r\n",diary.getUserid(), diary.getId(),diary.getTitle());	
		}
    	FileLogger.getInstance().logDiary(content);
    }
}
