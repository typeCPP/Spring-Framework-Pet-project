package com.company.entity;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(targetEntity = Department.class, optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "date_beg", nullable = false)
    private String dateBeg;

    @Column(name = "date_end", nullable = false)
    private String dateEnd;

    @Column(name = "date_end_real")
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
}
