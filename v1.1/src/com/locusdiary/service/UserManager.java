package com.locusdiary.service;

import java.util.List;

import com.locusdiary.module.User;

public interface UserManager {
	List<User>getFriends(User user);
	boolean addFriend(User user,User friendUser);
	void delFriend(User user,User friendUser);
	
	User userVerify(User user);
	User userRegister(User user);
	User getUser(User user);
}
