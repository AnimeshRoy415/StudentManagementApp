package com.studentManagementSystem.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagementSystem.exception.UserAlreadyExists;
import com.studentManagementSystem.model.CurrentUserSession;
import com.studentManagementSystem.model.User;
import com.studentManagementSystem.model.UserDTO;
import com.studentManagementSystem.repository.CurrentUserSessionDao;
import com.studentManagementSystem.repository.UserDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	private CurrentUserSessionDao currentusersessionDao;
	
	@Autowired
	private UserDao uDao;

	@Override
	public String userLogin(UserDTO userDto) {

		User u = uDao.findByMobile(userDto.getMobileNo());

		if(u==null) {
			return "please register before login...!!!";
		}
		Optional<CurrentUserSession> opt1 = currentusersessionDao.findById(u.getUserId());
		if(opt1.isPresent()) {
			throw new UserAlreadyExists("User Already loged in");
		}
		
		if(u.getPassword().equals(userDto.getPassword())) {
			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(u.getUserId(), key,LocalDateTime.now());;
			currentusersessionDao.save(currentUserSession);
			return currentUserSession.toString();
			
		}else return "Password does not match";
	
	}

}
