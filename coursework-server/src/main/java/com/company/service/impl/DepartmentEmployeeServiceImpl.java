package com.company.service.impl;

import com.company.entity.DepartmentEmployee;
import com.company.repository.DepartmentEmployeeRepository;
import com.company.service.DepartmentEmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    DepartmentEmployeeRepository departmentEmployeeRepository;

    public DepartmentEmployeeServiceImpl(DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public List<DepartmentEmployee> listDepartmentsEmployees() {
        return (List<DepartmentEmployee>) departmentEmployeeRepository.findAll();
    }

    @Override
    public DepartmentEmployee findById(Integer id) {
        return departmentEmployeeRepository.findById(id);
    }

    @Override
    public DepartmentEmployee addDepartmentEmployee(DepartmentEmployee departmentEmployee) {
        if (departmentEmployee.getDepartment().getId() != null && departmentEmployee.getEmployee().getId() != null) {
            if (departmentEmployeeRepository.findByDepartmentIdAndEmployeeId(departmentEmployee.getDepartment().getId(), departmentEmployee.getEmployee().getId()) == null) {
                return departmentEmployeeRepository.save(departmentEmployee);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void updateDepartmentEmployee(DepartmentEmployee departmentEmployee) {
        departmentEmployeeRepository.updateById(departmentEmployee.getId(), departmentEmployee.getDepartment(), departmentEmployee.getEmployee());
    }
}
