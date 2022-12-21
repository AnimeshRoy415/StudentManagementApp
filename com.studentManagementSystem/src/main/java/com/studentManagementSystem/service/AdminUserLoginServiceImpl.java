package com.studentManagementSystem.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagementSystem.exception.UserAlreadyExists;
import com.studentManagementSystem.model.CurrentAdminUserSession;
import com.studentManagementSystem.model.AdminUser;
import com.studentManagementSystem.model.AdminUserDTO;
import com.studentManagementSystem.repository.AdminCurrentUserSessionDao;
import com.studentManagementSystem.repository.AdminUserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminUserLoginServiceImpl implements AdminUserLoginService{
	
	@Autowired
	private AdminCurrentUserSessionDao currentusersessionDao;
	
	@Autowired
	private AdminUserDao uDao;

	@Override
	public String userLogin(AdminUserDTO userDto) {

		AdminUser u = uDao.findByMobile(userDto.getMobileNo());

		if(u==null) {
			return "Enter a valid mobile number";
		}
		Optional<CurrentAdminUserSession> opt1 = currentusersessionDao.findById(u.getUserId());
		if(opt1.isPresent()) {
			throw new UserAlreadyExists("User Already loged in");
		}
		
		if(u.getPassword().equals(userDto.getPassword())) {
			String key = RandomString.make(6);
			CurrentAdminUserSession currentUserSession = new CurrentAdminUserSession(u.getUserId(), key,LocalDateTime.now());;
			currentusersessionDao.save(currentUserSession);
			return currentUserSession.toString();
			
		}else return "Password does not match";
	
	}

}
