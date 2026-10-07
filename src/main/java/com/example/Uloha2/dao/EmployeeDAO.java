package com.example.Uloha2.dao;

import com.example.Uloha2.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
