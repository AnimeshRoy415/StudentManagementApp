package com.studentManagementSystem.service;

import java.util.List;



import com.studentManagementSystem.model.Address;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;


public interface StudentService {
	
	
	public Student addStudent(Student student);
	
	public Student updateStudent(Student student);
	
	public Student removeStudent(Student student);
	
	public Student viewStudent(Integer id);
	
	public List<Student> viewAllStudentByName(String name);
	
	public Student addAddress(Address address,Integer customerId);
	
	public List<Course> viewAllCourseByStudentId(Integer StudentId, String uniqueId);
	
	public Student deleteCourseFromStudent(String courseName,Integer studentId);

}
