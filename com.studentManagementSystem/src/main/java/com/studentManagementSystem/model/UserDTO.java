package com.studentManagementSystem.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
	
	@NotNull(message = "Enter Your Mobile Number")
	@Pattern(regexp = "[7896]{1}[0-9]{9}",message = "Input a valid mobile number")
	private String mobileNo;
	@NotNull(message = "Enter Your Password")
	private String password;
	@NotNull(message = "Please Enter Your Date of Birth(MM/DD/YYYY)")
	@Pattern(regexp = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$",message = "Input a valid date of birth")
	private String date_of_Birth;
	
}
