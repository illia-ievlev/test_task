package com.ievlev.test_task3.repository;

import com.ievlev.test_task3.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByName(String name);
}
