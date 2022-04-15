package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.Department;
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
@RequestMapping(value = "/departments", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class DepartmentController {

    private User clientUser;

    @Autowired
    public DepartmentController(User clientUser) {
        this.clientUser = clientUser;
    }

    @GetMapping
    public String getDepartments(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/departments/";

        ResponseEntity<Department[]> responseEntity = restTemplate.getForEntity(url, Department[].class);
        model.addAttribute("departments", responseEntity.getBody());

        return "department/departments";
    }

    @GetMapping("/{id}")
    public String getDepartment(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/departments/" + id;

        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url, Department.class);
        model.addAttribute("department", responseEntity.getBody());
        return "department/department";
    }

    @GetMapping("/new")
    public String newDepartment(Model model) {
        model.addAttribute("department", new Department());

        return "department/department-create";
    }

    @PostMapping("/create")
    public String createDepartment(@ModelAttribute("department") Department department) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/departments/add-department";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Department> request = new HttpEntity<>(department, httpHeaders);
        try {
            ResponseEntity<Department> responseEntity = restTemplate.postForEntity(url, request, Department.class);
            return "redirect:/departments";
        } catch (Exception exception) {
            exception.printStackTrace();
            return "redirect:/departments";
        }
    }

    @GetMapping("/{id}/edit")
    public String editDepartment(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/departments/" + id;
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url, Department.class);
        model.addAttribute("department", responseEntity.getBody());
        return "department/department-edit";
    }

    @PatchMapping("/{id}/patch")
    public String patchDepartment(@ModelAttribute("department") Department department, @PathVariable("id") Integer id) {
        department.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/departments/patch-department";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Department> request = new HttpEntity<>(department, httpHeaders);
        try {
            restTemplate.postForEntity(url + "?_method=patch", request, Department.class);
        } catch (Exception ignored) {
            return "redirect:/departments";
        }
        return "redirect:/departments";
    }

}
