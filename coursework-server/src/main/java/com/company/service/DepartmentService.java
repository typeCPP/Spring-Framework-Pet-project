package com.company.service;

import com.company.entity.Department;
import com.company.entity.Project;

import java.util.List;

public interface DepartmentService {
    List<Department> listDepartments();
    Department findById(Integer id);
    Department addDepartment(Department department);
    void updateDepartment(Department department);
}
