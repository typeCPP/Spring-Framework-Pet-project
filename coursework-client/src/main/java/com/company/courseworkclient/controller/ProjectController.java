package com.company.courseworkclient.controller;

import com.company.courseworkclient.model.Project;
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
@RequestMapping(value = "/projects", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class ProjectController {

    private User clientUser;

    @Autowired
    public ProjectController(User clientUser) {
        this.clientUser = clientUser;
    }

    @GetMapping
    public String getProjects(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/projects/";

        ResponseEntity<Project[]> responseEntity = restTemplate.getForEntity(url, Project[].class);
        model.addAttribute("projects", responseEntity.getBody());

        return "project/projects";
    }

    @GetMapping("/{id}")
    public String getProject(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/projects/" + id;

        ResponseEntity<Project> responseEntity = restTemplate.getForEntity(url, Project.class);
        model.addAttribute("project", responseEntity.getBody());
        return "project/project";
    }

    @GetMapping("/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());

        return "project/project-create";
    }

    @PostMapping("/create")
    public String createProject(@ModelAttribute("project") Project project) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/projects/add-project";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Project> request = new HttpEntity<>(project, httpHeaders);
        try {
            ResponseEntity<Project> responseEntity = restTemplate.postForEntity(url, request, Project.class);
        } catch (Exception ignored) {
            return "redirect:/projects";
        }
        return "redirect:/projects";
    }

    @GetMapping("/{id}/edit")
    public String editProject(@PathVariable("id") Integer id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/projects/" + id;
        ResponseEntity<Project> responseEntity = restTemplate.getForEntity(url, Project.class);
        model.addAttribute("project", responseEntity.getBody());
        return "project/project-edit";
    }

    @PatchMapping("/{id}/patch")
    public String patchProject(@ModelAttribute("project") Project project, @PathVariable("id") Integer id) {
        project.setId(id);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/projects/patch-project";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer_" + clientUser.getToken());
        HttpEntity<Project> request = new HttpEntity<>(project, httpHeaders);
        try {
            restTemplate.postForEntity(url + "?_method=patch", request, Project.class);
        } catch (Exception ignored) {
            return "redirect:/projects";
        }
        return "redirect:/projects";
    }
}
