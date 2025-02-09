package com.example.emp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.DTO.EmployeeDto;
import com.example.emp.DTO.JwtAuthResponse;
import com.example.emp.DTO.LoginDto;
import com.example.emp.Security.JwtTokenProvider;
import com.example.emp.Service.EmployeeService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto)
	{
		return new ResponseEntity<EmployeeDto>(employeeService.createEmployee(employeeDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> loginUser( @RequestBody LoginDto logindto)
	{
		Authentication authentication=
				authenticationManager.authenticate(
					 new UsernamePasswordAuthenticationToken(logindto.getEmail(), logindto.getPassword())	);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token =jwtTokenProvider.generateToken(authentication);
		return  ResponseEntity.ok(new JwtAuthResponse(token));
	}
}


