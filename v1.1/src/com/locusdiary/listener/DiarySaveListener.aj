package com.locusdiary.listener;

import com.locusdiary.module.GeneralDiary;

public aspect DiarySaveListener {
	pointcut captureDiarySaveOperation(GeneralDiary diary) : 
		call(void com.locusdiary.dao.DiaryDao.saveDiary(GeneralDiary)) 
		&& args(diary);
    
    //�쳣�����ʼ�� Advice
    before(GeneralDiary diary) : captureDiarySaveOperation(diary) {
    	String content = "";
    	if (!diary.isStatus()) {
    		content = String.format("[�޸��ռ�] [�û�]%s   ID��%s  ���⣺%s\r\n", diary.getUserid(), diary.getId(),diary.getTitle());	
		}else {
			content = String.format("[�½��ռ�] [�û�]%s   ID��%s  ���⣺%s\r\n",diary.getUserid(), diary.getId(),diary.getTitle());	
		}
    	FileLogger.getInstance().logDiary(content);
    }
}
