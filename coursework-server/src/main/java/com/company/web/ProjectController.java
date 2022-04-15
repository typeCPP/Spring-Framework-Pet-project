package com.company.web;

import com.company.entity.Project;
import com.company.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/projects", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH})
public class ProjectController {

    private ProjectService projectService;

    @GetMapping("/")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.listProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Integer projectId) {
        Project project = projectService.findById(projectId);
        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found.");
        }
    }

    @PostMapping(value = "/add-project", consumes = "application/json", produces = "application/json")
    public Project addProject(@RequestBody Project project) {
        Project addedProject = projectService.addProject(project);
        if(addedProject != null) {
            return addedProject;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
        }
    }

    @PatchMapping(value = "/patch-project", consumes = "application/json", produces = "application/json")
    public Project patchProject(@RequestBody Project project) {
        projectService.updateProject(project);
        return project;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }
}
