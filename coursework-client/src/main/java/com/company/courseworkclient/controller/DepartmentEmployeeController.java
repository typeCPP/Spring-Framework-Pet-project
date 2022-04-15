package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;

@Controller
@RequestMapping(value = "/departments-employees", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class DepartmentEmployeeController {

    private final String url = "http://localhost:8080/departments-employees/";

    private User clientUser;

    @Autowired
    public DepartmentEmployeeController(User clientUser) {
        this.clientUser = clientUser;
    }

    @GetMapping
    public String getDepartmentsEmployees(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<DepartmentEmployee[]> responseEntity = restTemplate.getForEntity(url, DepartmentEmployee[].class);
        Comparator<DepartmentEmployee> departmentEmployeeComparator = Comparator.comparing( departmentEmployee -> departmentEmployee.getDepartment().getId());
        DepartmentEmployee[] list = responseEntity.getBody();
        model.addAttribute("departmentsEmployees", list);

        return "department-employee/departments-employees";
    }

    @GetMapping("/new")
    public String newDepartmentEmployee(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        model.addAttribute("departmentEmployee", new DepartmentEmployee());

        ResponseEntity<Department[]> departmentResponseEntity = restTemplate.getForEntity("http://localhost:8080/departments/", Department[].class);
        model.addAttribute("departments", departmentResponseEntity.getBody());

        ResponseEntity<Employee[]> employeeResponseEntity = restTemplate.getForEntity("http://localhost:8080/employees/", Employee[].class);
        model.addAttribute("employees", employeeResponseEntity.getBody());

        return "department-employee/department-employee-create";
    }

    @PostMapping("/create")
    public String createDepartmentEmployee(@ModelAttribute("departmentEmployee") DepartmentEmployee departmentEmployee) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<DepartmentEmployee> request = new HttpEntity<>(departmentEmployee, httpHeaders);
        try {
            ResponseEntity<DepartmentEmployee> responseEntity = restTemplate.postForEntity(url + "add-department-employee", request, DepartmentEmployee.class);
        } catch (Exception ignored) {
            return "redirect:/departments-employees";
        }
        return "redirect:/departments-employees";
    }

    @GetMapping("/{id}/edit")
    public String editDepartmentEmployee(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DepartmentEmployee> responseEntity = restTemplate.getForEntity(url + id, DepartmentEmployee.class);

        model.addAttribute("departmentEmployee", responseEntity.getBody());

        ResponseEntity<Department[]> departmentResponseEntity = restTemplate.getForEntity("http://localhost:8080/departments/", Department[].class);
        model.addAttribute("departments", departmentResponseEntity.getBody());

        ResponseEntity<Employee[]> employeeResponseEntity = restTemplate.getForEntity("http://localhost:8080/employees/", Employee[].class);
        model.addAttribute("employees", employeeResponseEntity.getBody());
        return "department-employee/department-employee-edit";
    }

    @PatchMapping("/{id}/patch")
    public String patchDepartmentEmployee(@ModelAttribute("departmentEmployee") DepartmentEmployee departmentEmployee, @PathVariable("id") Integer id) {
        departmentEmployee.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<DepartmentEmployee> request = new HttpEntity<>(departmentEmployee, httpHeaders);
        try {
            restTemplate.postForEntity(url + "patch-department-employee" + "?_method=patch", request, DepartmentEmployee.class);
        } catch (Exception ignored) {
            return "redirect:/departments-employees";
        }
        return "redirect:/departments-employees";
    }

}
