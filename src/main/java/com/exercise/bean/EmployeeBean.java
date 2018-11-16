package com.exercise.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import com.exercise.department.Department;
import com.exercise.department.DepartmentService;
import com.exercise.employee.Employee;
import com.exercise.employee.EmployeeEntity;
import com.exercise.employee.EmployeeService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String locale;

	public EmployeeBean() {

	}

	private Employee selectedEmployee;

	private List<Employee> listAllEmployees;
	private List<Department> listAllDept;

	private boolean createEmployee = true;
	private boolean updateEmployee = false;
	private boolean english = true;
	private boolean german = false;

	@Inject
	private DepartmentService departmentService;
	private Department selectedDept;

	@Inject
	EmployeeService employeeService;

	private List<Employee> loadAllEmployees() {
		return employeeService.toBoms(employeeService.getAll());
	}

	@PostConstruct
	public void init() {
		setLocale("en");
		prepareEmployeeData();
		prepareDepartment();
	}

	private void prepareDepartment() {
		listAllDept = new ArrayList<Department>();
		listAllDept = loadAllDepts();
		if (listAllDept.size() > 0) {
			setSelectedDept(listAllDept.get(0));
		}
	}

	private List<Department> loadAllDepts() {
		return departmentService.toBoms(departmentService.getAll());
	}

	private void prepareEmployeeData() {
		listAllEmployees = new ArrayList<Employee>();
		listAllEmployees = loadAllEmployees();
		selectedEmployee = new Employee();
		selectedEmployee.setGender("undefine");
	}

	public void valueChangeMethod(ValueChangeEvent e) {
		selectedEmployee = employeeService
				.toBom(employeeService.findById(Integer.parseInt(e.getNewValue().toString())));
	}

	public void changeDepartment(ValueChangeEvent dept) {
		selectedDept = departmentService
				.toBom(departmentService.findById(Integer.parseInt(dept.getNewValue().toString())));
	}

	public String addEmployee() {
		Employee emp = new Employee();
		emp.setFirstName(this.selectedEmployee.getFirstName());
		emp.setLastName(this.selectedEmployee.getLastName());
		emp.setGender(this.selectedEmployee.getGender());
		emp.setEmail(this.selectedEmployee.getEmail());
		Department department = new Department();
		department.setDeptId(this.selectedDept.getDeptId());
		department.setDeptName(this.selectedDept.getDeptName());
		emp.setDepartment(department);
		employeeService.save(employeeService.toEntity(emp));
		listAllEmployees = loadAllEmployees();

		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public String editEmployee() {
		selectedEmployee.setDepartment(selectedDept);
		employeeService.update(employeeService.toEntity(selectedEmployee));

		selectedEmployee = new Employee();
		listAllEmployees = loadAllEmployees();

		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public void selectEmployeeForEdit(Employee emp) {
		setSelectedEmployee(emp);
		setSelectedDept(departmentService.toBom(departmentService.findById(emp.getDepartment().getDeptId())));
		setCreateEmployee(false);
		setUpdateEmployee(true);
	}

	public String deleteEmployee(Employee emp) {
		EmployeeEntity employeeEntity = employeeService.toEntity(emp);
		employeeService.removeEmployee(employeeEntity);
		listAllEmployees = loadAllEmployees();

		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public void seleteGerman() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot(); 
		viewRoot.setLocale(new Locale("de"));
		setLocale("de");
		setGerman(true);
		setEnglish(false);
	}
	public void seleteEnglish() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot(); 
		viewRoot.setLocale(new Locale("en"));
		setLocale("en");
		setGerman(false);
		setEnglish(true);
	}
	
}
