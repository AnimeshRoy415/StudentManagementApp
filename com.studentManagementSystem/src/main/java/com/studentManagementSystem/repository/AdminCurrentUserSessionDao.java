package com.studentManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studentManagementSystem.model.CurrentAdminUserSession;


public interface AdminCurrentUserSessionDao extends JpaRepository<CurrentAdminUserSession, Integer>{
	
   public Optional<CurrentAdminUserSession> findById(Integer userId);
   
   public CurrentAdminUserSession  findByUniqueId(String uniqueId);
   
   @Query("select c.Id from CurrentUserSession c where c.UserId=?1")
   public String findByUserId(Integer userId);  
   
   
}
