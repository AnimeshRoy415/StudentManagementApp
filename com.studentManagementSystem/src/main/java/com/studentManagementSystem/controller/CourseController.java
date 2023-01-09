package com.studentManagementSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;
import com.studentManagementSystem.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	

	@PostMapping("/")
	public Course saveCourseHandler(@Valid @RequestBody Course course) {
		
		return courseService.addCourse(course);
		
	}
	
	
	@PutMapping("/asigncorse/student/{courseId}/{studentId}")
	public String addCourseToStudent(@PathVariable Integer courseId,@PathVariable Integer studentId) {
		
		return courseService.addCourseToStudent(courseId, studentId);
		
	}
	

}
