package com.ievlev.test_task3.service;

import com.ievlev.test_task3.exceptions.DepartmentWithSpecifiedNameNotFoundException;
import com.ievlev.test_task3.exceptions.HeadOfDepartmentDoesNotExistException;
import com.ievlev.test_task3.model.Degree;
import com.ievlev.test_task3.model.Department;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNameOfHeadOfDepartment() {
        String departmentName = "Test Department";
        Lector headOfDepartment = new Lector("John Doe", Degree.PROFESSOR, BigDecimal.valueOf(12000));
        Department department = new Department(departmentName, headOfDepartment, Collections.emptyList());
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.of(department));
        String headOfDepartmentName = departmentService.getNameOfHeadOfDepartment(departmentName);
        assertEquals(headOfDepartment.getName(), headOfDepartmentName);
    }

    @Test
    void testGetNameOfHeadOfDepartment_DepartmentNotFound() {
        String departmentName = "Non-existent Department";
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.empty());

        assertThrows(DepartmentWithSpecifiedNameNotFoundException.class, () -> departmentService.getNameOfHeadOfDepartment(departmentName));
    }


    @Test
    void testGetNameOfHeadOfDepartment_HeadOfDepartmentNotFound() {
        String departmentName = "Test Department";
        Department department = new Department(departmentName, null, Collections.emptyList());
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.of(department));

        assertThrows(HeadOfDepartmentDoesNotExistException.class, () -> departmentService.getNameOfHeadOfDepartment(departmentName));
    }

    @Test
    void testGetDepartmentByName() {
        String departmentName = "Test Department";
        Department expectedDepartment = new Department(departmentName, null, Collections.emptyList());
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.of(expectedDepartment));

        Department actualDepartment = departmentService.getDepartmentByName(departmentName);

        assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    void testGetDepartmentByName_DepartmentNotFound() {
        String departmentName = "Non-existent Department";
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.empty());

        assertThrows(DepartmentWithSpecifiedNameNotFoundException.class, () -> departmentService.getDepartmentByName(departmentName));
    }

    @Test
    void testGetLectorsFromDepartment() {
        String departmentName = "Test Department";
        Lector lector1 = new Lector("John Doe", Degree.ASSISTANT, BigDecimal.valueOf(120000));
        Lector lector2 = new Lector("Jane Smith", Degree.PROFESSOR, BigDecimal.valueOf(3400));
        List<Lector> expectedLectors = Arrays.asList(lector1, lector2);
        Department department = new Department(departmentName, null, expectedLectors);
        when(departmentRepository.findDepartmentByName(departmentName)).thenReturn(Optional.of(department));
        List<Lector> actualLectors = departmentService.getLectorsFromDepartment(departmentName);
        assertEquals(expectedLectors, actualLectors);
    }

    @Test
    void testGetLectorsFromDepartment_NullDepartmentName() {
        assertThrows(IllegalArgumentException.class, () -> departmentService.getLectorsFromDepartment(null));
    }

    @Test
    void testGetNameOfHeadOfDepartment_NullDepartmentName(){
        assertThrows(IllegalArgumentException.class, ()-> departmentService.getLectorsFromDepartment(null));
    }

}
