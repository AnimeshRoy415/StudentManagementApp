package com.studentManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	
	@NotNull(message = "please fill course Name")
	private String courseName;
	
	@NotNull(message = "please fill course description")
	private String courseDescription;
	
	@NotNull(message = "please fill course type")
	private String courseType;
	
	@NotNull(message = "please fill course Duration")
	private String courseDuration;
	
	@NotNull(message = "please fill course Topic")
	private String courseTopic;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Student> students= new ArrayList<>();
	
}
