package com.locusdiary.listener;


import com.locusdiary.module.User;

public aspect UserRegisterListener {
	pointcut captureUserRegisterOperation(User user) : 
		call(User com.locusdiary.dao.DiaryDao.userRegister(User)) && args(user);
    
    //异常对象初始化 Advice
    before(User user) : captureUserRegisterOperation(user) {
    	String content = "";
    	content = String.format("[用户注册] %s   %s\r\n", user.getUsername(), user.getEmail());	
		FileLogger.getInstance().logUser(content);
    }
}
