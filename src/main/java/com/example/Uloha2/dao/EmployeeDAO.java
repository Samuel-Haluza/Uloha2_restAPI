package com.example.Uloha2.dao;

import com.example.Uloha2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
}
