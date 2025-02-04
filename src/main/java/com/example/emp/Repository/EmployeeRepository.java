package com.example.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // You can define custom query methods here if needed (e.g., find by email, first name, etc.)
    // For example:
    // Optional<Employee> findByEmail(String email);
}