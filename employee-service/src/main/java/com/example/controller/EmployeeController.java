package com.example.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/all")
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployees();
	}
	
//	
//	@GetMapping
//	public Page<Employee> getAllEmployee(
//			@RequestParam(defaultValue = "0") int pageNumber,
//			@RequestParam(defaultValue = "10") int pageSize){
//		return employeeService.getAllEmployees(pageNumber, pageSize);
//	}
//	
	
	@GetMapping
	public Page<Employee> getAllEmployee(
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "firstName") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir
			){
		return employeeService.getAllEmployees(pageNumber, pageSize,sortBy,sortDir);
	}
	


}
