package com.springboot.rest.employee.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.employee.dto.OrderedDto;
import com.springboot.rest.employee.entity.Employee;
import com.springboot.rest.employee.exception.ServiceException;
import com.springboot.rest.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeControler {
	private static final String NULL = "NULL";
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/")
	public ResponseEntity<Employee> save(@RequestBody Employee employee) throws ServiceException {
		Employee emp = employeeService.saveEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> update(@PathVariable long id, @RequestBody Employee employee) throws ServiceException {
		if(employee.getId()!=null && employee.getId()!=id)
			throw new RuntimeException("The employee.idd dont match with parameter id");
			
		employee.setId(id);
		Employee emp = employeeService.updateEmployee(employee);

		return new ResponseEntity<Employee>(emp, HttpStatus.OK); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) throws ServiceException {
		Employee employee = employeeService.deleteByEmployeeId(id);
		if(employee!=null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.notFound().build(); 
	}
	
	@GetMapping("/all/{filterBy}/{value}")
	public List<Employee> list(@PathVariable String filterBy, @PathVariable String value){
		return employeeService.listEmployee(filterBy, value);
	}
	
	@GetMapping("/all")
	public List<Employee> list(){
		return employeeService.listEmployee(NULL, null);
	}
	
	@GetMapping("/positions")
	public List<OrderedDto> listAllPositions(){
		return employeeService.allPositions();
	}

}
