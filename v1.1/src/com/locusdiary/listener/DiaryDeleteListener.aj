package com.locusdiary.listener;

import com.locusdiary.module.GeneralDiary;

public aspect DiaryDeleteListener {
	pointcut captureDiaryDeleteOperation(GeneralDiary diary) : 
		call(void com.locusdiary.dao.DiaryDao.deleteDiary(GeneralDiary)) 
		&& args(diary);
    
    //�쳣�����ʼ�� Advice
    before(GeneralDiary diary) : captureDiaryDeleteOperation(diary) {
    	String content = "";
    	content = String.format("[ɾ���ռ�] [�û�]%s   ID��%s  ���⣺%s\r\n", diary.getUserid(), diary.getId(),diary.getTitle());	
		FileLogger.getInstance().logDiary(content);
    }
}
