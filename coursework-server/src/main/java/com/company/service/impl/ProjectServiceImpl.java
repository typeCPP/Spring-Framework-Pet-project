package com.company.service.impl;

import com.company.entity.Department;
import com.company.entity.Project;
import com.company.repository.DepartmentRepository;
import com.company.repository.ProjectRepository;
import com.company.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    DepartmentRepository departmentRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, DepartmentRepository departmentRepository) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Project> listProjects() {
        return (List<Project>) projectRepository.findAll();
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project addProject(Project project) {
        if(departmentRepository.findById(project.getDepartment().getId()) != null) {
            return projectRepository.save(project);
        } else {
            return null;
        }
    }

    @Override
    public void updateProject(Project project) {
        projectRepository.updateProjectById(project.getId(), project.getName(), project.getDepartment(), project.getDateBeg(), project.getDateEnd(), project.getDateEndReal());
    }
}
