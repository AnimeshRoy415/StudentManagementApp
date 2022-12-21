package com.studentManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer studentId;
	
	@NotNull(message = "please input a valid name")
//	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)(\\s)$){1,26}$"+"(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",message = "please input a valid name")
	private String name;
	
//	@NotNull(message = "please input a valid name")
//	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",message = "please input a valid name")
//	private String lastName;
	
	@NotNull(message = "Please Enter Your Date of Birth")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Input a valid date of birth")
	private String date_of_Birth;
	
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	
	@Pattern(regexp = "^(.+)@(\\S+)$",message = "Input a valid email address")
	private String email;
	
	@Pattern(regexp = "[7896]{1}[0-9]{9}",message = "Input a valid mobile number")
	private String mobileNumber;
	
	@NotNull(message = "please input a valid name")
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",message = "please input a valid name")
	private String parentFirstName;
	
	@NotNull(message = "please input a valid name")
	@Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",message = "please input a valid name")
	private String parentLastName;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresslist= new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL)
//	@JsonIgnore
	private List<Course> courses= new ArrayList<>();
	

}
