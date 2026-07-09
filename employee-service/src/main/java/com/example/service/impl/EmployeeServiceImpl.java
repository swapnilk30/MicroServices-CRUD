package com.example.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<Employee> getAllEmployees(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Employee> page = employeeRepository.findAll(pageable);
		return page;
	}

	@Override
	public Page<Employee> getAllEmployees(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending() ;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		return employeeRepository.findAll(pageable);
	}

	

}
