package com.company.repository;

import com.company.entity.Department;
import com.company.entity.DepartmentEmployee;
import com.company.entity.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentEmployeeRepository extends CrudRepository<DepartmentEmployee, Long> {
    @Query(value = "select d from DepartmentEmployee d where d.department.id = ?1")
    List<DepartmentEmployee> findByDepartmentId(Integer departmentId);

    @Query(value = "select d from DepartmentEmployee d where d.employee.id = ?1")
    List<DepartmentEmployee> findByEmployeeId(Integer employeeId);

    @Query(value = "select d from DepartmentEmployee d where d.id=?1")
    DepartmentEmployee findById(Integer id);

    @Query(value = "select d from DepartmentEmployee d where d.department.id = ?1 and d.employee.id =?2")
    DepartmentEmployee findByDepartmentIdAndEmployeeId(Integer departmentId, Integer employeeId);

    @Transactional
    @Modifying
    @Query(value = "update DepartmentEmployee d set d.department = :department, d.employee = :employee where d.id = :id")
    void updateById(@Param("id") Integer id, @Param("department") Department department, @Param("employee") Employee employee);
}
