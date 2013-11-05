package com.qqdiary.app.service;

/**
 * 用户验证、注册借口定义
 * @author Administrator
 *
 */
public interface UserVerify {
	boolean userVerify(String name, String key);
	void userRegister();
}
