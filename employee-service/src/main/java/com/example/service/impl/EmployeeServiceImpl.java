package com.example.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		String email = employee.getEmail();
		
		if(employeeRepository.existsByEmail(email)) {
			throw new RuntimeException("Email already exists");
		}

		Employee savedEmployee = employeeRepository.save(employee);
		return savedEmployee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

}
