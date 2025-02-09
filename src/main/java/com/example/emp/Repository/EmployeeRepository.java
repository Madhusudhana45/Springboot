package com.example.emp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

    // You can define custom query methods here if needed (e.g., find by email, first name, etc.)
    // For example:
    // Optional<Employee> findByEmail(String email);
}