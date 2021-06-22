package com.springboot.rest.employee.dto;

import com.springboot.rest.employee.entity.Person;

public class EmployeeDto {
	private Long id;
	private Long salary;
	private Person person;
	
	public EmployeeDto() {}

	public EmployeeDto(Long id, Person person, Long salary) {
		this.id     = id;
		this.person = person;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
