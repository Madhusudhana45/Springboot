package com.example.emp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.DTO.EmployeeDto;
import com.example.emp.Service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	// build Add employee
	@PostMapping("/employees")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
		return new ResponseEntity<EmployeeDto>(savedEmployee,HttpStatus.CREATED);
	}
	
	// Build Api to get Employee REST API
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id")long id)
	{
		
		EmployeeDto employeeDto = employeeService.getEmployeeById(id);
	 return new ResponseEntity<EmployeeDto>(employeeDto,HttpStatus.OK);
	}
	
	// Build API to get  Get All Employees
	
	@GetMapping("/getAllemployees")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees()
	{
			List<EmployeeDto> employees=employeeService.getAllEmployees();
			return ResponseEntity.ok(employees);
	}
	
	
	// Build API to get Update Emploees
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeDto updateEmployee)
	{
		 EmployeeDto employeeDto= employeeService.updateEmployee(id, updateEmployee);
		 return  ResponseEntity.ok(employeeDto);
	}
	
	//  Build API to get Delete Emplopyee
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> deleteEmployee(long id)
	{
		employeeService.deleteEmployee(id);;
		return   ResponseEntity.ok("Employee deleted Successfully");
	}
	
}
