package com.springboot.rest.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	public List<Employee> findByPersonNameContaining(String name);
	
	public List<Employee> findByPositionNameContaining(String name);
	
}
