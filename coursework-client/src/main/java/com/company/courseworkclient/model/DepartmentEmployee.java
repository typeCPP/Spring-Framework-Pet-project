package com.company.courseworkclient.model;

public class DepartmentEmployee {

    private Integer id;
    private Department department;
    private Employee employee;

    public DepartmentEmployee(Department department, Employee employee) {
        this.department = department;
        this.employee = employee;
    }

    public DepartmentEmployee(Integer id, Department department, Employee employee) {
        this.id = id;
        this.department = department;
        this.employee = employee;
    }

    public DepartmentEmployee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "DepartmentEmployee{" +
                "id=" + id +
                ", department=" + department .toString()+
                ", employee=" + employee.toString() +
                '}';
    }
}
