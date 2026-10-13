package com.example.Uloha2.dao;

import com.example.Uloha2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
}
