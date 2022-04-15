package com.company.repository;

import com.company.entity.Department;
import com.company.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    @Query(value = "select d from Department d where d.name = ?1")
    List<Department> findByName(String name);

    @Query(value = "select d from Department d where d.id=?1")
    Department findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into departments (name) values (:name)", nativeQuery = true)
    void insertDepartment(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update Department d set d.name = :name where d.id = :id")
    void updateById(@Param("id") Integer id, @Param("name") String name);

    @Transactional
    @Modifying
    void deleteById(@Param("id") Integer id);
}
