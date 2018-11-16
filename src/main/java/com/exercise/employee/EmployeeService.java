package com.exercise.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.exercise.department.Department;
import com.exercise.department.DepartmentEntity;

@Stateless
public class EmployeeService {

	@PersistenceContext
	private EntityManager em;
	
	public EmployeeService() {
		
	}
	
	public List<EmployeeEntity> getAll(){
		TypedQuery<EmployeeEntity> query = em.createQuery("Select e FROM EmployeeEntity e", EmployeeEntity.class);
		return query.getResultList();
	}
	
	public void save(EmployeeEntity emp) {
		if(emp != null) {
			this.em.persist(emp);
		}
	}
	
	public void update(EmployeeEntity emp) {
		this.em.merge(emp);
	}
	
	public void removeEmployee(EmployeeEntity emp) {
		this.em.remove(em.merge(emp));
	}
	
	public void remove(Integer id) {
		EmployeeEntity emp = findById(id);
		if(emp != null) {
			em.remove(emp);
		}
	}
	
	public EmployeeEntity findById(Integer id) {
		return em.find(EmployeeEntity.class, id);
	}
	
	public Employee toBom(EmployeeEntity entity) {
		Employee employee = new Employee();
		employee.setId(entity.getId());
		employee.setFirstName(entity.getFirstName());
		employee.setLastName(entity.getLastName());
		employee.setEmail(entity.getEmail());
		employee.setGender(entity.getGender());
		Department department = new Department();
		department.setDeptId(entity.getDepartment().getDeptId());
		department.setDeptName(entity.getDepartment().getDeptName());
		employee.setDepartment(department);
		return employee;
	}
	
	public List<Employee> toBoms(List<EmployeeEntity> entities) {
		List<Employee> employees = new ArrayList<>();
		for (EmployeeEntity entity : entities) {
			employees.add(toBom(entity));
		}
		return employees;
	}
	
	public EmployeeEntity toEntity(Employee employee) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setId(employee.getId());
		entity.setFirstName(employee.getFirstName());
		entity.setLastName(employee.getLastName());
		entity.setEmail(employee.getEmail());
		entity.setGender(employee.getGender());
		DepartmentEntity departmentEntity  = new DepartmentEntity();
		departmentEntity.setDeptId(employee.getDepartment().getDeptId());
		departmentEntity.setDeptName(employee.getDepartment().getDeptName());
		entity.setDepartment(departmentEntity);
		return entity;
	}
	
	public List<EmployeeEntity> toEntities(List<Employee> emps){
		List<EmployeeEntity> employeeEntities = new ArrayList<>();
		emps.stream()
			.map(each -> toEntity(each))
			.filter(Objects::nonNull)
			.forEach(entity -> employeeEntities.add(entity));
		return employeeEntities;
	}
	
	
	public Long countTotalEmployee() {
		TypedQuery<Long> query = em.createNamedQuery("countEmployee", Long.class);
		return query.getSingleResult();
	}
}
