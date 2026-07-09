package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	

	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = employeeService.saveEmployee(employee);
		return saveEmployee;
		
	}
	
	@GetMapping
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployees();
	}
}
