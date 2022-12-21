package com.studentManagementSystem.service;

import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;

public interface CourseService {
	
	public Course addCourse(Course course);
	
	public Student addCourseToStudent(Integer courseId, Integer studentId);
	

}
