package com.locusdiary.listener;


import com.locusdiary.module.User;

public aspect UserRegisterListener {
	pointcut captureUserRegisterOperation(User user) : 
		call(User com.locusdiary.dao.DiaryDao.userRegister(User)) && args(user);
    
    //�쳣�����ʼ�� Advice
    before(User user) : captureUserRegisterOperation(user) {
    	String content = "";
    	content = String.format("[�û�ע��] %s   %s\r\n", user.getUsername(), user.getEmail());	
		FileLogger.getInstance().logUser(content);
    }
}
