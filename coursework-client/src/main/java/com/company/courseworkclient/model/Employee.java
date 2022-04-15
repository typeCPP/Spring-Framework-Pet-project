package com.company.courseworkclient.model;

public class Employee {
    private Integer id;

    private String firstName;

    private String lastName;

    private String patherName;

    private String position;

    private Integer salary;

    public Employee(String firstName, String lastName, String patherName, String position, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public Employee(Integer id, String firstName, String lastName, String patherName, String position, Integer salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public Employee() {
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

    public String getFullName() {
        return lastName + " " + firstName + " " + patherName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
