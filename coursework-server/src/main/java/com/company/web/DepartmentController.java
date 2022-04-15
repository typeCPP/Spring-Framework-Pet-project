package com.company.web;

import com.company.entity.Department;
import com.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/departments", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class DepartmentController {
    DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.listDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Integer departmentId) {
        Department department = departmentService.findById(departmentId);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
        }
    }

    @PostMapping(value = "/add-department", consumes = "application/json", produces = "application/json")
    public Department addDepartment(@RequestBody Department department) {
        Department addedDepartment = departmentService.addDepartment(department);
        if(addedDepartment != null) {
            return addedDepartment;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error while adding department.");
        }
    }

    @PatchMapping(value = "/patch-department", consumes = "application/json", produces = "application/json")
    public Department patchDepartment(@RequestBody Department department) {
        departmentService.updateDepartment(department);
        return department;
    }
}
