package com.springboot.rest.employee.dto;

import java.util.ArrayList;
import java.util.List;

import com.springboot.rest.employee.entity.Employee;

public class OrderedDto {
	private Long id;
	private String name;
	private List<EmployeeDto> employees = new ArrayList<>();
	
	public OrderedDto() {}
	
	public OrderedDto(Long id, String name) {
		this.id   = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee emp) {
		emp.setPosition(null);
		employees.add(new EmployeeDto(emp.getId(), emp.getPerson(), emp.getSalary()));
	}
	
}
