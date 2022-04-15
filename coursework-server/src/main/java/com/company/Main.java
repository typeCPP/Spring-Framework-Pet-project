package com.company;

import com.company.entity.Department;
import com.company.entity.DepartmentEmployee;
import com.company.entity.Employee;
import com.company.entity.Project;
import com.company.repository.DepartmentEmployeeRepository;
import com.company.repository.DepartmentRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner test(DepartmentRepository departmentRepository, DepartmentEmployeeRepository departmentEmployeeRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        return args -> {
          /*  Department department = new Department("department");
            departmentRepository.save(department);
            Employee employee = new Employee("Andrey", "Usanov", "Romanovich", "CEO", 100);
            employeeRepository.save(employee);
            departmentEmployeeRepository.save(new DepartmentEmployee(department, employee));
            projectRepository.save(new Project("projectName", department, "asasd", "sdfds", "dsfsd"));
            ArrayList<Department> array = (ArrayList<Department>) departmentRepository.findAll();*/
            //departmentRepository.deleteById(1);
        };
    }
}
