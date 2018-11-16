package com.exercise.department;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class DepartmentService {

	@PersistenceContext
	private EntityManager em;
	
	public DepartmentService() {
		
	}
	
	public List<DepartmentEntity> getAll(){
		TypedQuery<DepartmentEntity> query = em.createQuery("Select d From DepartmentEntity d", DepartmentEntity.class);
		return query.getResultList();
	}
	
	public DepartmentEntity findById(Integer id) {
		return em.find(DepartmentEntity.class, id);
	}
	
	public Department toBom (DepartmentEntity departmentEntity) {
		Department department = new Department();
		department.setDeptId(departmentEntity.getDeptId());
		department.setDeptName(departmentEntity.getDeptName());
		return department;
	}
	
	public List<Department> toBoms (List<DepartmentEntity> departmentEntites) {
		List<Department> departments = new ArrayList<>();
		for (DepartmentEntity dept : departmentEntites) {
			departments.add(toBom(dept));
		}
		return departments;
	}
}
