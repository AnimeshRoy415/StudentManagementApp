package com.studentManagementSystem.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.studentManagementSystem.model.Address;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;

import com.studentManagementSystem.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService sService;
//	private AddressService aService;
	
	@PostMapping("/")
	public Student saveStudentHandler(@Valid @RequestBody Student student) {
		
		return sService.addStudent(student);
	}
	
	
	@PutMapping("/")
	public Student updateStudent(@Valid @RequestBody Student student) {
		
		return sService.updateStudent(student);
	}
	
	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable Integer studentId) {
		
		return sService.viewStudent(studentId);
		
	}
	
	
	@DeleteMapping("/")
	public Student deleteStudentDetails(@Valid @RequestBody Student student) {
		
		return sService.removeStudent(student);
		
	}
	
	@PutMapping("/addAddress/{studentId}")
	public Student addAddressStudent(@Valid @RequestBody Address address, @PathVariable Integer studentId) {
		return sService.addAddress(address,studentId);
	}

	@GetMapping("/name/{studentName}")
	public List<Student> getAllStudents(@PathVariable String studentName) {
		
		return sService.viewAllStudentByName(studentName);
		
	}
	
	@PutMapping("/courses/{studentId}/{uniqueId}")
	public List<Course> addAddressStudent( @PathVariable Integer studentId, String uniqueId) {
		return sService.viewAllCourseByStudentId(studentId, uniqueId);
	}
	
	
	
	
	@PutMapping("asigncourse/{studentId}/{courseName}")
	public Student asignCourseToStudent(@PathVariable Integer studentId, String courseName) {
		
		return sService.addCourseToTheStudent(studentId, courseName);
		
	}
	
	@DeleteMapping("/courses/{courseName}/{studentId}")
	public Student removeCourseFromStudent( @PathVariable String courseName,Integer studentId) {
		return sService.deleteCourseFromStudent(courseName, studentId);
		}

}
