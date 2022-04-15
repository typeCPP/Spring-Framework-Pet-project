package com.company.service;

import com.company.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> listEmployees();
    Employee findById(Integer id);
    Employee addEmployee(Employee employee);
    void updateEmployee(Employee employee);
}
