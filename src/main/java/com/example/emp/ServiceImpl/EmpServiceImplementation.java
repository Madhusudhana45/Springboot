package com.example.emp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.emp.DTO.EmployeeDto;
import com.example.emp.Exception.ResourceNotFoundException;
import com.example.emp.Mapper.EmployeeMapper;
import com.example.emp.Model.Employee;
import com.example.emp.Repository.EmployeeRepository;
import com.example.emp.Service.EmployeeService;
@Service
public class EmpServiceImplementation implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public EmployeeDto createEmployee( EmployeeDto employeeDto) {
		
		employeeDto.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
		
		Employee employee =EmployeeMapper.EmployeeDtoToEmployee(employeeDto);
		
		Employee savedEmployee= employeeRepository.save(employee);
		 return EmployeeMapper.EmployeeToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
	
		Employee employee=employeeRepository.findById(id).orElseThrow(
			()-> new ResourceNotFoundException("Employee is not Exist :"+id));
		return EmployeeMapper.EmployeeToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
	List<Employee> employees=employeeRepository.findAll();
		
		return employees.stream().map(employee-> EmployeeMapper.EmployeeToEmployeeDto(employee)).collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto updateEmployee) {
		
	Employee employee=	employeeRepository.findById(id).orElseThrow(
			()-> new ResourceNotFoundException("Employee Not found :"+id));
		
	employee.setFirstName(updateEmployee.getFirstName());
	employee.setLastName(updateEmployee.getLastName());
	employee.setEmail(updateEmployee.getEmail());
	 if (updateEmployee.getPassword() != null && !updateEmployee.getPassword().isEmpty()) {
	        employee.setPassword(passwordEncoder.encode(updateEmployee.getPassword()));
	    }
	
	Employee updateEmployeeObj= employeeRepository.save(employee);
		
	return EmployeeMapper.EmployeeToEmployeeDto(updateEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long id) {
		
		Employee employee=employeeRepository.findById(id).orElseThrow(
			()-> new ResourceNotFoundException("Employee is not Exist :"+id));
		
		employeeRepository.deleteById(id);
	}

}
