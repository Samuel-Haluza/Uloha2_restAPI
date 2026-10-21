package com.example.Uloha2.service;

import com.example.Uloha2.dao.EmployeeDAO;
import com.example.Uloha2.entity.Employee;
import com.example.Uloha2.exception.EmailAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeDAO.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        Optional<Employee> employeeWithEmail = employeeDAO.findByEmail(employee.getEmail());
        if (employeeWithEmail.isPresent()
                && !Objects.equals(employeeWithEmail.get().getId(), employee.getId())) {
            throw new EmailAlreadyExistsException(employee.getEmail());
        }
        return employeeDAO.save(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeDAO.deleteById(id);
    }
}
