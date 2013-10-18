package com.qqdiary.app.service;

import com.qqdiary.app.module.User;

/**
 * 用户验证、注册借口定义
 * @author Administrator
 *
 */
public interface UserVerify {
	boolean userVerify(User user);
	void userRegister(User user);
}
