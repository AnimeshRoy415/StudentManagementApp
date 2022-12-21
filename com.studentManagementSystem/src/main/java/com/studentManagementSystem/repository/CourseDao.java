package com.studentManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentManagementSystem.model.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
	
	public Course findByCourseId(Integer cusrseId);
	
//	public List<Course> findByCourses(Course  course);
	
	public Course  findByCourseName(String courseName);

}
