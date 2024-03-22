package com.ievlev.test_task3.repository;

import com.ievlev.test_task3.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    List<Lector> findByNameContainingIgnoreCase(String name);
}
