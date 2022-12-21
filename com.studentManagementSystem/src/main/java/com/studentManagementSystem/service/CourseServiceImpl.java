package com.studentManagementSystem.service;


import java.util.Optional;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentManagementSystem.exception.UserAlreadyExists;
import com.studentManagementSystem.model.Course;
import com.studentManagementSystem.model.Student;
import com.studentManagementSystem.repository.CourseDao;
import com.studentManagementSystem.repository.StudentDao;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao cDao;
	
	@Autowired
	private StudentDao sDao;

	@Override
	public Course addCourse(Course course) {
		
		Course getCourse= cDao.findByCourseName(course.getCourseName());
		
		if(getCourse==null) {
			
			 return cDao.save(course);

		}
		
		
		else throw new UserAlreadyExists("Course already present");
	}

	@Override
	public Student addCourseToStudent(Integer courseId, Integer studentId) {
		
		Optional<Course> getCourse= cDao.findById(courseId);
		Optional<Student> getStudent= sDao.findById(studentId);
		
		if(getCourse.isPresent() && getStudent.isPresent()) {
			
			System.out.println(getCourse.get());
			System.out.println(getStudent.get());
			
			Course course= getCourse.get();
			Student student= getStudent.get();
			student.getCourses().add(course);
			sDao.save(student);
			
		}
		
		
		Optional<Student> optStudent= sDao.findById(studentId);
		return optStudent.get();
	}

}
