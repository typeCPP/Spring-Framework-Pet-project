package com.company.service;

import com.company.entity.Project;

import java.util.List;

public interface ProjectService {
    List<Project> listProjects();
    Project findById(Integer id);
    Project addProject(Project project);
    void updateProject(Project project);
}
