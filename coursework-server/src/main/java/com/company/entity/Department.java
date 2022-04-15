package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", targetEntity = Project.class)
    private List<Project> projectList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department", targetEntity = DepartmentEmployee.class)
    private List<DepartmentEmployee> departmentEmployeeList;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }

    public List<DepartmentEmployee> getDepartmentEmployeeList() {
        return departmentEmployeeList;
    }

    public void setDepartmentEmployeeList(List<DepartmentEmployee> departmentEmployeeList) {
        this.departmentEmployeeList = departmentEmployeeList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
