package com.ievlev.test_task3.initializer;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

public abstract class IntegrationTestBase {

    public static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:8.0.31");
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.test.database.replace", () -> "none");
        registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
        registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
    }
}
