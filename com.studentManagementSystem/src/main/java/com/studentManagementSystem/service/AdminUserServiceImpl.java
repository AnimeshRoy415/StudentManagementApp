package com.studentManagementSystem.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.studentManagementSystem.exception.UserAlreadyExists;
import com.studentManagementSystem.model.CurrentAdminUserSession;
import com.studentManagementSystem.model.AdminUser;
import com.studentManagementSystem.repository.AdminCurrentUserSessionDao;
import com.studentManagementSystem.repository.AdminUserDao;

@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserDao uDao;
	
	@Autowired
	private AdminCurrentUserSessionDao cuserDao;

	@Override
	public AdminUser saveUer(AdminUser user) {		
		AdminUser u = uDao.findByMobile(user.getMobile());
		if(u==null) return uDao.save(user);
		else throw new UserAlreadyExists("User Already Exists");
	}

	@Override
	public AdminUser updateUserCredential(AdminUser user, String key) {
		CurrentAdminUserSession cuser =  cuserDao.findByUniqueId(key);
		if(cuser==null) throw new UserAlreadyExists("user not loged in");
		Optional<AdminUser> opt = uDao.findById(cuser.getUserId());
		if(opt.isEmpty()) throw new UserAlreadyExists("user not found");
		AdminUser userWithSameNumber = uDao.findByMobile(user.getMobile());
		if(userWithSameNumber!=null) throw new UserAlreadyExists("one user find with the same mobile number");
		AdminUser saveUser = uDao.save(user);
		cuserDao.delete(cuser);
		uDao.delete(opt.get());
		return saveUser;
	}

	@Override
	public String userLogout(String key) {
		CurrentAdminUserSession cuser =  cuserDao.findByUniqueId(key);
		if(cuser==null) throw new UserAlreadyExists("user not loged in");
		cuserDao.delete(cuser);
		return "Logged Out successfully";
	}


	
}
