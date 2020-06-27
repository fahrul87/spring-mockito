package com.fahrul.springmockito.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fahrul.springmockito.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
