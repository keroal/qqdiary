package com.qqdiary.app.service;

/**
 * �û���֤��ע���ڶ���
 * @author Administrator
 *
 */
public interface UserVerify {
	boolean userVerify(String name, String key);
	void userRegister();
}
