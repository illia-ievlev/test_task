package com.ievlev.test_task3.repository;


import com.ievlev.test_task3.initializer.IntegrationTestBase;
import com.ievlev.test_task3.model.Lector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
public class LectorRepositoryIntegrationTest extends IntegrationTestBase {
    private LectorRepository lectorRepository;

    @Autowired
    public LectorRepositoryIntegrationTest(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    @Sql("classpath:sql/data.sql")
    @Test
    public void getCorrectNameByPattern() {
        List<Lector> lectorList = lectorRepository.findByNameContainingIgnoreCase("Smith");
        assertThat(lectorList.size() == 1
                && lectorList.get(0).getName().equals("John Smith"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void getCorrectNameByPatternIgnorecase() {
        List<Lector> lectorList = lectorRepository.findByNameContainingIgnoreCase("SMITH");
        assertThat(lectorList.size() == 1
                && lectorList.get(0).getName().contains("Smith")
        );
    }

    @Test
    public void returnEmptyIfNothingFound() {
        List<Lector> lectorList = lectorRepository.findByNameContainingIgnoreCase("not existent user");
        assertThat(lectorList.isEmpty());
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void returnAllWhenEmptyStringWasPassed() {
        List<Lector> lectorList = lectorRepository.findByNameContainingIgnoreCase("");
        assertThat(lectorList.size() == 20);
    }

    @Test
    public void returnEmptyListIfNullWasPassed() {
        List<Lector> lectorList = lectorRepository.findByNameContainingIgnoreCase(null);
        assertThat(lectorList.isEmpty());
    }

}
