package com.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "pather_name")
    private String patherName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", targetEntity = DepartmentEmployee.class)
    private List<DepartmentEmployee> departmentEmployeeList;

    public Employee(String firstName, String lastName, String patherName, String position, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
    }

    public List<DepartmentEmployee> getDepartmentEmployeeList() {
        return departmentEmployeeList;
    }

    public void setDepartmentEmployeeList(List<DepartmentEmployee> departmentEmployeeList) {
        this.departmentEmployeeList = departmentEmployeeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
