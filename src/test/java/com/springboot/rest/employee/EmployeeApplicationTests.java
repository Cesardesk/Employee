package com.springboot.rest.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.springboot.rest.employee.controler.EmployeeControler;
import com.springboot.rest.employee.entity.Employee;
import com.springboot.rest.employee.entity.Person;
import com.springboot.rest.employee.exception.ServiceException;

@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	private EmployeeControler controller;
	
	
	@BeforeEach
	private void validateConexions() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	void listAllEmployees() {
		Printable print = (o) -> employeeFormat((Employee)o);
		List<Employee> list = controller.list();
		list.forEach(e -> print.print(e));
	}
	
	@Test
	void insertNewEmployee() throws ServiceException {
		Person p   = new Person();
		Employee e = new Employee();
		
		p.setName("Cesar");
		p.setLastName("Diaz");
		p.setAddress("Av. always alive");
		p.setCellphone("123-3215-6545");
		p.setCityName("Tunja");
		
		e.setPerson(p);
		e.setSalary(3200000l);
		
		ResponseEntity<Employee> newEmployeeEntity = controller.save(e);
		Employee newEmployee = newEmployeeEntity.getBody();
		
		assertNotNull(newEmployee.getId());
		
		assertEquals(p.getName(), newEmployee.getPerson().getName());
		
		controller.delete(newEmployee.getId());
		
		List<Employee> lista = controller.list();
		
		long count = lista.stream().filter(emp -> emp.getId().equals(newEmployee.getId())).count();
		
		assertEquals(0, count);
		
	}
	
	@Test
	void updateNewEmployee() throws ServiceException {
		List<Employee> lista = controller.list();
		final String lastName = "Diaz";
		
		long count = lista.stream().filter(emp -> emp.getPerson().getLastName().equals(lastName)).count();
		
		assertEquals(0, count);
		
		Employee e = lista.get(0);
		e.getPerson().setLastName(lastName);
		
		controller.update(e.getId(), e);
		
		lista = controller.list();
		
		count = lista.stream().filter(emp -> emp.getPerson().getLastName().equals(lastName)).count();
		
		assertEquals(1, count);
		
	}
	
	
	
	private static void employeeFormat(Employee e) {
		System.out.println(
				String.format("EMPLOYEE - [ ID: %s, Name: %s, Address: %s, Cell: %s, City: %s ] ", e.getId(), e.getPerson().getName(),
						e.getPerson().getAddress(), e.getPerson().getCellphone(), e.getPerson().getCityName()));
	}
	
	interface Printable {
		void print(Object o);
	}

}
