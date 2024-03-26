package com.ievlev.test_task3.repository;

import com.ievlev.test_task3.model.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @EntityGraph(value = "Department.headOfDepartment")
    Optional<Department> findDepartmentWithHeadOfDepartmentByName(String name);

    @EntityGraph(value = "Department.lectorList")
    Optional<Department> findDepartmentWithLectorsByName(String name);
}
