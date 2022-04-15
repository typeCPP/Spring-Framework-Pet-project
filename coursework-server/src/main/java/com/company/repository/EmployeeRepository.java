package com.company.repository;

import com.company.entity.Department;
import com.company.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    @Query(value = "select e from Employee e where e.firstName = ?1")
    List<Employee> findAllByFirstName(@Param("name") String name);

    @Query(value = "select e from Employee e where e.lastName = ?1")
    List<Employee> findAllByLastName(@Param("name") String name);

    @Query(value = "select e from Employee e where e.position = ?1")
    List<Employee> findAllByPosition(@Param("position") String position);

    @Query(value = "select e from Employee e where e.salary > ?1")
    List<Employee> findAllBySalaryBiggerThan(@Param("salary") Integer salary);

    @Query(value = "select e from Employee e where e.salary < ?1")
    List<Employee> findAllBySalaryLessThan(@Param("salary") Integer salary);

    @Query(value = "select e from Employee e where e.salary = ?1")
    List<Employee> findAllBySalary(@Param("salary") Integer salary);

    @Query(value = "select e from Employee e where e.id=?1")
    Employee findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "delete from Employee where position = :position")
    void deleteAllByPosition(@Param("position") String position);

    @Transactional
    @Modifying
    @Query(value="insert into employees(first_name, last_name, pather_name, position, salary) values(:firstName, :lastName, :patherName, :position, :salary)", nativeQuery = true)
    void insertEmployee(@Param("firstName") String firstName, @Param("lastName") String lastName,
                        @Param("patherName") String patherName, @Param("position") String position, @Param("salary") Integer salary);

    @Transactional
    @Modifying
    @Query(value = "update Employee e set e.firstName = :firstName, e.lastName = :lastName, e.patherName = :patherName, e.position = :position, e.salary = :salary where e.id = :id")
    void updateEmployeeById(@Param("id") Integer id, @Param("firstName") String firstName, @Param("lastName") String lastName,
                            @Param("patherName") String patherName, @Param("position") String position, @Param("salary") Integer salary);

    @Transactional
    @Modifying
    void deleteById(@Param("id") Integer id);
}
