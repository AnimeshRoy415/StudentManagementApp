package com.studentManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentManagementSystem.model.Address;
import com.studentManagementSystem.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer> {
	
	
//	public List<Customer> findByAddresslist(List<Address> addresslist);
	
	 public Student findByMobileNumber(String mobileNumber);
	 public List<Student> findByName(String name);

}
