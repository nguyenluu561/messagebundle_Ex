package com.exercise.employee;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.exercise.department.DepartmentEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@NamedQuery(name="countEmployee", query="Select COUNT(e.id) FROM EmployeeEntity e")
@NamedStoredProcedureQuery(name = "findEmployeeByName", procedureName = "employee.spGetEmployeeByName",
parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
				@StoredProcedureParameter (mode = ParameterMode.OUT, name = "", type = List.class)})

@Getter
@Setter
public class EmployeeEntity {
	
	@Column(name="empId")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	
	@ManyToOne
	@JoinColumn(name="deptid")
	private DepartmentEntity department;
	
}
