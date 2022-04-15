package com.company.courseworkclient.model;

public class Project {
    private Integer id;

    private String name;

    private Department department;

    private String dateBeg;

    private String dateEnd;

    private String dateEndReal;

    public Project(String name, Department department, String dateBeg, String dateEnd, String dateEndReal) {
        this.name = name;
        this.department = department;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateEndReal = dateEndReal;
    }

    public Project() {
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department departmentId) {
        this.department = departmentId;
    }

    public String getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(String dateBeg) {
        this.dateBeg = dateBeg;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateEndReal() {
        return dateEndReal;
    }

    public void setDateEndReal(String dateEndReal) {
        this.dateEndReal = dateEndReal;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", dateBeg='" + dateBeg + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", dateEndReal='" + dateEndReal + '\'' +
                '}';
    }
}
