package com.studentManagementSystem.service;

import com.studentManagementSystem.model.AdminUser;

public interface AdminUserService {
	
	public AdminUser saveUer(AdminUser user);
	
	public AdminUser updateUserCredential(AdminUser user,String key);
	
	public String userLogout(String key);
}
