package com.springboot.rest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.employee.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
