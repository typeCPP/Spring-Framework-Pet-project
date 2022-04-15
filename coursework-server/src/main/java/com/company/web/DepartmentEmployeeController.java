package com.company.web;

import com.company.entity.DepartmentEmployee;
import com.company.service.DepartmentEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/departments-employees", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class DepartmentEmployeeController {
    DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    public void setDepartmentEmployeeService(DepartmentEmployeeService departmentEmployeeService) {
        this.departmentEmployeeService = departmentEmployeeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartmentEmployee>> getAllDepartmentsEmployees() {
        List<DepartmentEmployee> departmentEmployees = departmentEmployeeService.listDepartmentsEmployees();
        return new ResponseEntity<>(departmentEmployees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEmployee> getDepartmentEmployee(@PathVariable("id") Integer id) {
        DepartmentEmployee departmentEmployee = departmentEmployeeService.findById(id);
        if (departmentEmployee != null) {
            return new ResponseEntity<>(departmentEmployee, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department employee not found.");
        }
    }
    @PostMapping(value = "/add-department-employee", consumes = "application/json", produces = "application/json")
    public DepartmentEmployee addDepartmentEmployee(@RequestBody DepartmentEmployee departmentEmployee) {
        DepartmentEmployee addedDepartmentEmployee = departmentEmployeeService.addDepartmentEmployee(departmentEmployee);
        if(addedDepartmentEmployee != null) {
            return addedDepartmentEmployee;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while adding department-employee.");
        }
    }

    @PatchMapping(value = "/patch-department-employee", consumes = "application/json", produces = "application/json")
    public DepartmentEmployee patchDepartmentEmployee(@RequestBody DepartmentEmployee departmentEmployee) {
        departmentEmployeeService.updateDepartmentEmployee(departmentEmployee);
        return departmentEmployee;
    }
}
