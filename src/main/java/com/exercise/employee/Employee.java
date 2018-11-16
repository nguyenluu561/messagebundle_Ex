package com.exercise.employee;

import javax.validation.constraints.NotNull;

import com.exercise.department.Department;
import com.exercise.validation.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Employee {
	private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	@NotNull
	@Email
	private String email;
	private Department department;
	
}
