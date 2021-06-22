package com.springboot.rest.employee.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.rest.employee.dto.OrderedDto;
import com.springboot.rest.employee.entity.Employee;
import com.springboot.rest.employee.entity.Person;
import com.springboot.rest.employee.entity.Position;
import com.springboot.rest.employee.exception.ServiceException;
import com.springboot.rest.employee.repository.EmployeeRepository;
import com.springboot.rest.employee.repository.PersonRepository;
import com.springboot.rest.employee.repository.PositionRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PositionRepository positionRepository;
	
	public Employee saveEmployee(Employee employee) throws ServiceException {
		Person   person   = savePerson(employee.getPerson());
		Position position = savePosition(employee.getPosition());
		
		employee.setPerson(person);
		employee.setPosition(position);
		
		Employee emp = null;
		try {
			emp = employeeRepository.save(employee); 
		}
		catch (Exception e) {
			throw new ServiceException("Failed saving 'Employee' : " + e.getMessage());
		}
		
		return emp;
	}
	
	public Employee updateEmployee(Employee employee) throws ServiceException {
		Employee currentEmployee = existEmployee(employee.getId());
		
		// The person id is setted
		employee.getPerson().setId(currentEmployee.getPerson().getId());
		
		return saveEmployee(employee);
	}
	
	public Employee deleteByEmployeeId(Long id) throws ServiceException {
		Employee employee = existEmployee(id);
		employeeRepository.deleteById(id);
		return employee;
	}
	
	public List<Employee> listEmployee(final String filterBy, String value){
		switch (filterBy) {
		case "position":
			return employeeRepository.findByPositionNameContaining(value);
		case "name":
			return employeeRepository.findByPersonNameContaining(value);
		default:
			return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "person.name"));
		}
	}
	
	public List<OrderedDto> allPositions() {
		List<Employee> employees = employeeRepository.findAll(Sort.by("position.name").ascending().and(Sort.by("salary").descending()));
		
		if(employees.size()==0) return Collections.emptyList();
		
		List<OrderedDto> result = new ArrayList<>();
		OrderedDto dto = null;
		
		for(Employee e:employees) {
			if(e.getPosition()==null) continue;
			
			if(dto == null || !dto.getId().equals(e.getPosition().getId())) {
				dto = new OrderedDto(e.getPosition().getId(), e.getPosition().getName());
				result.add(dto);
			}
			
			dto.addEmployee(e);	
		}
		
		return result;
	}
	
	private Employee existEmployee(Long id) throws ServiceException {
		Optional<Employee> opt = employeeRepository.findById(id);
		if(!opt.isPresent())
			throw new ServiceException("Failed updating 'Employee' : Doesn't exist the employee");
		
		return opt.get();
	}
	
	private Person savePerson(Person person) throws ServiceException {
		//Person validations
		// * the person cannot be null
		// * the id is not validated because is a JsonIgnored Property, always is null
		// * no more properties are validated
		if(person==null) 
			throw new ServiceException("The 'Person' information is mandatory ");
		
		try {
			person =personRepository.save(person);
		}catch (Exception e) {
			throw new ServiceException("Failed saving 'Person' : " + e.getMessage());
		}
		return person;
	}
	
	private Position savePosition(Position position) throws ServiceException {
		//Position Validations
		// * position can be null (I assume it) 
		// * position can exists
		// * position can be new
		if(position!=null && position.getId()!=null) {
			Optional<Position> opt =  positionRepository.findById(position.getId());
			if(opt.isPresent()) {
				position = opt.get();
			}
			else {
				try {
					position = positionRepository.save(position);
				}catch (Exception e) {
					throw new ServiceException("Failed saving 'Person' : " + e.getMessage());
				}
			}
		}
		return position;
	}
	
}
