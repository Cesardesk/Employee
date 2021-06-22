package com.springboot.rest.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest.employee.entity.Position;

public interface PositionRepository extends JpaRepository<Position, Long>{

}
