package com.studentManagementSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentManagementSystem.model.AdminUser;

@Repository
public interface AdminUserDao extends JpaRepository<AdminUser, Integer>{
	
	public AdminUser findByMobile(String mobile);

//	public User findByUserId(Integer userId);
}
