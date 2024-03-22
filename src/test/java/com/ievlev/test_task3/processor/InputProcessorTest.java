package com.ievlev.test_task3.processor;

import com.ievlev.test_task3.initializer.IntegrationTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"run.loop=false"})
public class InputProcessorTest extends IntegrationTestBase {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @Sql("classpath:sql/data.sql")
    @Test
    public void whoIsHeadOfDepartmentShouldReturnCorrectData() {
        provideInput("Who is head of department Department A");
        assertThat(outputStream.toString().contains("John Smith"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void whoIsHeadOfDepartmentShouldReturnCorrectDataIfDepartmentNotFound() {
        provideInput("Who is head of department hello");
        assertThat(outputStream.toString().contains("can't find department with name :hello"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showStatisticsShouldReturnCorrectData() {
        provideInput("Show Department A statistics");
        assertThat(outputStream.toString().contains("assistants - 2\n" +
                " associate professors - 1\n" +
                " professors - 1"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showStatisticsShouldReturnCorrectDataIfDepartmentNotFound() {
        provideInput("Show hello statistics");
        assertThat(outputStream.toString().contains("can't find department with name :hello"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showTheAverageSalaryShouldReturnCorrectData() {
        provideInput("Show the average salary for the department Department A");
        assertThat(outputStream.toString().contains("59750"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showTheAverageSalaryShouldReturnCorrectDataIfDepartmentNotFound() {
        provideInput("Show the average salary for the department hello");
        assertThat(outputStream.toString().contains("can't find department with name :hello"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showCountOfEmployeeForShouldReturnCorrectData() {
        provideInput("Show count of employee for Department A");
        assertThat(outputStream.toString().contains("4"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void showCountOfEmployeeForShouldReturnCorrectDataIfDepartmentNotFound() {
        provideInput("Show count of employee for hello");
        assertThat(outputStream.toString().contains("can't find department with name :hello"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void globalSearchByTemplateShouldReturnCorrectData() {
        provideInput("Global search by David");
        assertThat(outputStream.toString().contains("David Taylor"));
    }

    @Test
    @Sql("classpath:sql/data.sql")
    public void globalSearchByTemplateShouldReturnCorrectDataIfNothingWasFound() {
        provideInput("Global search by helloWorld");
        assertThat(outputStream.toString().contains("Answer: []"));
    }

    private void provideInput(String data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
}
