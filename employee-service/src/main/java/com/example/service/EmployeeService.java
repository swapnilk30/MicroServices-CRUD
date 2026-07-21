package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	
	List<Employee> getAllEmployees();
	
	Page<Employee> getAllEmployees(int pageNumber,int pageSize);
	
	Page<Employee> getAllEmployees(int pageNumber,int pageSize,String sortBy ,String sortDir);

	Employee getEmployeeById(Long id);

}
