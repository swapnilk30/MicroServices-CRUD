package com.example.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@Slf4j
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

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){

		log.info("EmployeeController.getEmployeeById()");
		return ResponseEntity.ok(employeeService.getEmployeeById(id));
	}


}
