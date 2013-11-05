package com.locusdiary.listener;

import com.locusdiary.module.GeneralDiary;

public aspect DiaryDeleteListener {
	pointcut captureDiaryDeleteOperation(GeneralDiary diary) : 
		call(void com.locusdiary.dao.DiaryDao.deleteDiary(GeneralDiary)) 
		&& args(diary);
    
    //异常对象初始化 Advice
    before(GeneralDiary diary) : captureDiaryDeleteOperation(diary) {
    	String content = "";
    	content = String.format("[删除日记] [用户]%s   ID：%s  标题：%s\r\n", diary.getUserid(), diary.getId(),diary.getTitle());	
		FileLogger.getInstance().logDiary(content);
    }
}
