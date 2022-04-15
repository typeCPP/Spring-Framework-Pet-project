package com.company.web;

import com.company.entity.Employee;
import com.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/employees", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
        }
    }

    @PostMapping(value = "/add-employee", consumes = "application/json", produces = "application/json")
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.addEmployee(employee);
        if(addedEmployee != null) {
            return addedEmployee;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while adding employee.");
        }
    }

    @PatchMapping(value = "/patch-employee", consumes = "application/json", produces = "application/json")
    public Employee patchEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }
}
