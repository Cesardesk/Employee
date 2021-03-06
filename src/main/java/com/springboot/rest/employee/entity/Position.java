package com.springboot.rest.employee.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	@GeneratedValue
	private Long id;
	private String name;
	
	public Position() {}

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
	
}
