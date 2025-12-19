package com.example.employee_registration.repository;

import com.example.employee_registration.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);

    Page<Employee> findByNameContainingIgnoreCase(String search, Pageable pageable);
}

