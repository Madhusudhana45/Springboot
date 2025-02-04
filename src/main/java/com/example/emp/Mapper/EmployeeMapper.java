package com.example.emp.Mapper;

import com.example.emp.DTO.EmployeeDto;
import com.example.emp.Model.Employee;

public class EmployeeMapper {

	
	public static EmployeeDto EmployeeToEmployeeDto(Employee employee)
	{
		 return new EmployeeDto(
				 employee.getId(),
				 employee.getFirstName(),
				 employee.getLastName(),
				 employee.getEmail(),
				 employee.getPassword()
				 );
	}
	
	public static Employee EmployeeDtoToEmployee(EmployeeDto employeeDto) 
	{
			return new Employee(
					employeeDto.getId(),
					employeeDto.getFirstName(),
					employeeDto.getLastName(),
					employeeDto.getEmail(),
					employeeDto.getPassword()
					);
			 
	}
	
}
