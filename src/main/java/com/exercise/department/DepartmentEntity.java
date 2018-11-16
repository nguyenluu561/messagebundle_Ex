package com.exercise.department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="department")
@Getter
@Setter
public class DepartmentEntity {
	@Column(name="deptId" )
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer deptId;
	private String deptName;
}
