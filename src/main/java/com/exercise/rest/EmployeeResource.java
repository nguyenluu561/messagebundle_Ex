package com.exercise.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.exercise.employee.Employee;
import com.exercise.employee.EmployeeService;

@Stateless
@Path("employee")
public class EmployeeResource {
	@Inject
	EmployeeService employeeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employee> readAll() {
		return employeeService.toBoms(employeeService.getAll());
		//return Response.ok().build();
	}
	
	@GET
	@Path("{EmployeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee read(@PathParam("EmployeeId")Integer id) {
		return employeeService.toBom(employeeService.findById(id));
		//return Response.ok().build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response employee (@Valid Employee employee) {
		employeeService.save(employeeService.toEntity(employee));
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee (Employee employee) {
		employeeService.update(employeeService.toEntity(employee));
		return Response.ok().build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeEmployee (Employee employee) {
		employeeService.remove(employee.getId());
		return Response.status(Response.Status.GONE).build();
	}
}
