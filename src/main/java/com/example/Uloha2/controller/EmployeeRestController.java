package com.example.Uloha2.controller;

import com.example.Uloha2.entity.Employee;
import com.example.Uloha2.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Employees", description = "CRUD operácie so zamestnancami")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    @Operation(summary = "Získať všetkých zamestnancov")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "Získať zamestnanca podľa ID")
    public ResponseEntity<Employee> findById(@PathVariable Integer id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employees")
    @Operation(summary = "Pridať nového zamestnanca")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        employee.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }

    @PutMapping("/employees/{id}")
    @Operation(summary = "Aktualizovať zamestnanca")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee) {
        if (employeeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employee.setId(id);
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Zmazať zamestnanca")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (employeeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
