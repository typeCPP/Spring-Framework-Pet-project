package com.company.service;

import com.company.entity.DepartmentEmployee;

import java.util.List;

public interface DepartmentEmployeeService {
    List<DepartmentEmployee> listDepartmentsEmployees();
    DepartmentEmployee findById(Integer id);
    DepartmentEmployee addDepartmentEmployee(DepartmentEmployee departmentEmployee);
    void updateDepartmentEmployee(DepartmentEmployee departmentEmployee);
}
