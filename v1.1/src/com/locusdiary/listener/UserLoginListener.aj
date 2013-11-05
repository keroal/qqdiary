package com.locusdiary.listener;

import com.locusdiary.module.User;

public aspect UserLoginListener {
    pointcut captureUserVerifyOperation(User user) :
        call(User com.locusdiary.dao.DiaryDao.userVerify(User))&& args(user);
    
    
    before(User user) : captureUserVerifyOperation(user) {
    	String content = "";
    	content = String.format("[ÓÃ»§µÇÂ½] %s\r\n", user.getUserid());	
		FileLogger.getInstance().logUser(content);
    }
}