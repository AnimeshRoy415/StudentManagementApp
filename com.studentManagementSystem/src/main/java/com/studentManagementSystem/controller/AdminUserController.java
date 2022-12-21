package com.studentManagementSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentManagementSystem.model.AdminUser;
import com.studentManagementSystem.model.AdminUserDTO;
import com.studentManagementSystem.service.AdminUserLoginService;
import com.studentManagementSystem.service.AdminUserService;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
	private AdminUserService uService;
	
	@Autowired
	private AdminUserLoginService ulogService;
	
	@PostMapping("/regisrtration")

	public ResponseEntity<AdminUser> saveUserController(@Valid @RequestBody AdminUser user) {
		AdminUser responseUser =  uService.saveUer(user);
		return new  ResponseEntity<AdminUser>(responseUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginController(@Valid @RequestBody AdminUserDTO userDto) {
		String msg =  ulogService.userLogin(userDto);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateUser/{key}")
	public ResponseEntity<AdminUser> updateUserCredentialController(@Valid @RequestBody AdminUser user, @PathVariable String key) {
		AdminUser responseUser =  uService.updateUserCredential(user, key);
		return new ResponseEntity<AdminUser>(responseUser,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout/{key}")
	public ResponseEntity<String> userLogoutController(@Valid @PathVariable String key) {
		String msg =  uService.userLogout(key);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	
}
