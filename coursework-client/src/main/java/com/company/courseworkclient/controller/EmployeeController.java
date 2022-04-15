package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.Employee;
import com.company.courseworkclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = "/employees", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class EmployeeController {

    private User clientUser;

    @Autowired
    public EmployeeController(User clientUser) {
        this.clientUser = clientUser;
    }

    @GetMapping
    public String getEmployees(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees/";

        ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
        model.addAttribute("employees", responseEntity.getBody());

        return "employee/employees";
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees/" + id;

        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class);
        model.addAttribute("employee", responseEntity.getBody());
        return "employee/employee";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/employee-create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees/add-employee";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Employee> request = new HttpEntity<>(employee, httpHeaders);
        try {
            ResponseEntity<Employee> responseEntity = restTemplate.postForEntity(url, request, Employee.class);
            return "redirect:/employees";
        } catch (Exception ignored) {
            return "redirect:/employees";
        }
    }

    @GetMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees/" + id;
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class);
        model.addAttribute("employee", responseEntity.getBody());
        return "employee/employee-edit";
    }

    @PatchMapping("/{id}/patch")
    public String patchEmployee(@ModelAttribute("employee") Employee employee, @PathVariable("id") Integer id) {
        employee.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/employees/patch-employee";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Employee> request = new HttpEntity<>(employee, httpHeaders);
        try {
            restTemplate.postForEntity(url + "?_method=patch", request, Employee.class);
        } catch (Exception ignored) {
            return "redirect:/employees";
        }
        return "redirect:/employees";
    }

}
