package com.example.emp.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.emp.Exception.ResourceNotFoundException;
import com.example.emp.Model.Employee;
import com.example.emp.Repository.EmployeeRepository;

@Service
public class CustomUserServiceDetails  implements   UserDetailsService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee  employee=employeeRepository.findByEmail(email).orElseThrow(
			() -> new ResourceNotFoundException(String.format("user email not found : %s is not found", email)));
		
		Set<String> roles = new HashSet<String>();
		roles.add("ROLE_ADMIN");
		return new User(employee.getEmail(),employee.getPassword(), userAuthorities(roles));
	}
	
	
	private Collection<? extends GrantedAuthority> userAuthorities(Set<String> roles){
		return  roles.stream().map(
			role -> new SimpleGrantedAuthority(role)
			).collect(Collectors.toList());
				
	}

}

	
