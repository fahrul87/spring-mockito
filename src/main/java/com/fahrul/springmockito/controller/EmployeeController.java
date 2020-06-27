package com.fahrul.springmockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fahrul.springmockito.dao.EmployeeRepository;
import com.fahrul.springmockito.model.Employee;
import com.fahrul.springmockito.model.Response;



@RestController
@RequestMapping("/EmployeeService")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repository;

	@PostMapping("/addEmployee")
	public Response addEmployee(@RequestBody Employee employee) {
		repository.save(employee);
		return new Response(employee.getId() + "inserted", Boolean.TRUE);
	}
	
	@GetMapping("/getEmployees")
	public Response getAllEmployees() {
		List<Employee> employees = repository.findAll();
		return new Response("record counts : "+ employees.size(), Boolean.TRUE);
	}
}
