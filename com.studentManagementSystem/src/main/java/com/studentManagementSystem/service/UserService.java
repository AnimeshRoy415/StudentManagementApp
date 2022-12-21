package com.studentManagementSystem.service;

import com.studentManagementSystem.model.User;

public interface UserService {
	
	public User saveUer(User user);
	
	public User updateUserCredential(User user,String key);
	
	public String userLogout(String key);
}
