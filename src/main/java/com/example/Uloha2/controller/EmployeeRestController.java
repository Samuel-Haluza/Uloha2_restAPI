package com.example.Uloha2.controller;

import com.example.Uloha2.entity.Employee;
import com.example.Uloha2.exception.ObjectNotFoundException;
import com.example.Uloha2.response.ResponseFactory;
import com.example.Uloha2.response.ResponseWrapper;
import com.example.Uloha2.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    private final ResponseFactory response;

    public EmployeeRestController(EmployeeService employeeService, ResponseFactory response) {
        this.employeeService = employeeService;
        this.response = response;
    }

    @GetMapping("/employees")
    @Operation(summary = "Získať všetkých zamestnancov")
    public ResponseEntity<ResponseWrapper<List<Employee>>> findAll() {
        return response.ok("Úspešne načítané", employeeService.findAll());
    }

    @GetMapping("/employees/{id}")
    @Operation(summary = "Získať zamestnanca podľa ID")
    public ResponseEntity<ResponseWrapper<Employee>> findById(@PathVariable Integer id) {
        return employeeService.findById(id)
                .map(employee -> response.ok("Zamestnanec úspešne načítaný", employee))
                .orElseThrow(() -> new ObjectNotFoundException("Zamestnanec", id));
    }

    @PostMapping("/employees")
    @Operation(summary = "Pridať nového zamestnanca")
    public ResponseEntity<ResponseWrapper<Employee>> add(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        return response.created("Zamestnanec úspešne vytvorený", employeeService.save(employee));
    }

    @PutMapping("/employees/{id}")
    @Operation(summary = "Aktualizovať zamestnanca")
    public ResponseEntity<ResponseWrapper<Employee>> update(
            @PathVariable Integer id, @Valid @RequestBody Employee employee) {
        if (employeeService.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Zamestnanec", id);
        }

        employee.setId(id);
        return response.ok("Zamestnanec úspešne aktualizovaný", employeeService.save(employee));
    }

    @DeleteMapping("/employees/{id}")
    @Operation(summary = "Zmazať zamestnanca")
    public ResponseEntity<ResponseWrapper<Void>> delete(@PathVariable Integer id) {
        if (employeeService.findById(id).isEmpty()) {
            throw new ObjectNotFoundException("Zamestnanec", id);
        }

        employeeService.deleteById(id);
        return response.ok("Zamestnanec úspešne vymazaný");
    }
}
