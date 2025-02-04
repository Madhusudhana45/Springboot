package com.example.emp.Service;

import java.util.List;

import com.example.emp.DTO.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long id);
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto updateEmployee( Long id, EmployeeDto employeeDto);
	
	void deleteEmployee(Long id);
	
}
