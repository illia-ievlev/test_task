package com.ievlev.test_task3.repository;

import com.ievlev.test_task3.initializer.IntegrationTestBase;
import com.ievlev.test_task3.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
public class DepartmentRepositoryIntegrationTest extends IntegrationTestBase {
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentRepositoryIntegrationTest(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Sql("classpath:sql/data.sql")
    @Test
    public void getCorrectDepartmentByName() {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName("Department A");
        assertThat(departmentOptional.isPresent() && departmentOptional.get().getName().equals("Department A"));
    }

    @Test
    public void returnEmptyIfNothingFound() {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName("not existent user");
        assertThat(departmentOptional.isEmpty());
    }

    @Test
    public void returnEmptyOptionalIfNullWasPassed() {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName(null);
        assertThat(departmentOptional.isEmpty());
//        assertThrows(IllegalArgumentException.class, () -> departmentRepository.findDepartmentByName(null));
    }

    @Test
    public void returnEmptyOptionalIfEmptyStringWasPassed() {
        Optional<Department> departmentOptional = departmentRepository.findDepartmentByName("");
        assertThat(departmentOptional.isEmpty());
    }
}
