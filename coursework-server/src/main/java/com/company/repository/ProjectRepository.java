package com.company.repository;

import com.company.entity.Department;
import com.company.entity.Project;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Query(value = "select p from Project p where p.dateBeg=?1")
    List<Project> findAllByDateBeg(String dateBeg);

    @Query(value = "select p from Project p where p.dateEnd=?1")
    List<Project> findAllByDateEnd(String dateEnd);

    @Query(value = "select p from Project p where p.dateEndReal=?1")
    List<Project> findAllByDateEndReal(String dateEndReal);

    @Query(value = "select p from Project p where p.name=?1")
    List<Project> findAllByName(String Name);

    @Query(value = "select p from Project p where p.id=?1")
    Project findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "insert into Projects (name, department_id, date_beg, date_end, date_end_real) values (:name, :department_id, :date_beg, :date_end, :date_end_real)", nativeQuery = true)
    void insertProject(@Param("name") String name, @Param("department_id") Integer departmentId,
                       @Param("date_beg") String dateBeg, @Param("date_end") String dateEnd,
                       @Param("date_end_real") String dateEndReal);

    @Modifying
    @Transactional
    @Query(value = "update Project p set p.name=:name, p.department=:department, p.dateBeg=:date_beg, p.dateEnd=:date_end, p.dateEndReal=:date_end_real where p.id=:id")
    void updateProjectById(@Param("id") Integer id, @Param("name") String name, @Param("department") Department department,
                           @Param("date_beg") String dateBeg, @Param("date_end") String dateEnd,
                           @Param("date_end_real") String dateEndReal);

    @Transactional
    @Modifying
    @Query(value = "delete from Project where name = :name")
    void deleteProjectByName(@Param("name") String name);


}
