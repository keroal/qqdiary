package com.qqdiary.app.service;

import com.qqdiary.app.module.User;

/**
 * �û���֤��ע���ڶ���
 * @author Administrator
 *
 */
public interface UserVerify {
	boolean userVerify(User user);
	void userRegister(User user);
}
