package com.exercise.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.exercise.employee.EmployeeService;

@Stateless
@Path("statistic")
public class Statistic {

	@Inject
	EmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Long countTotal() {
		return employeeService.countTotalEmployee();
	}
}
