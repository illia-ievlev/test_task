//package com.ievlev.test_task3.service;
//
//import com.ievlev.test_task3.model.Degree;
//import com.ievlev.test_task3.model.Department;
//import com.ievlev.test_task3.model.Lector;
//import com.ievlev.test_task3.repository.LectorRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//public class LectorServiceTest {
//
//    @Mock
//    private LectorRepository lectorRepository;
//
//    @Mock
//    private DepartmentService departmentService;
//
//    @InjectMocks
//    private LectorService lectorService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testGetAverageSalaryOfDepartment() {
//        String departmentName = "Test Department";
//        Lector lector1 = new Lector("John Doe", Degree.ASSISTANT, BigDecimal.valueOf(1000));
//        Lector lector2 = new Lector("Jane Smith", Degree.PROFESSOR, BigDecimal.valueOf(2000));
//        Department department = new Department(departmentName, null, Arrays.asList(lector1, lector2));
//        when(departmentService.getDepartmentByName(departmentName)).thenReturn(department);
//        BigDecimal averageSalary = lectorService.getAverageSalaryOfDepartment(departmentName);
//        assertEquals(BigDecimal.valueOf(1500), averageSalary);
//    }
//
//    @Test
//    void testGetAverageSalaryOfDepartment_NullDepartmentName() {
//        assertThrows(IllegalArgumentException.class, () -> lectorService.getAverageSalaryOfDepartment(null));
//    }
//
//    @Test
//    void testGetNumberOfEmployeeForDepartment() {
//        String departmentName = "Test Department";
//        Lector lector1 = new Lector("John Doe", Degree.ASSISTANT, BigDecimal.valueOf(1000));
//        Lector lector2 = new Lector("Jane Smith", Degree.PROFESSOR, BigDecimal.valueOf(2000));
//        Department department = new Department(departmentName, null, Arrays.asList(lector1, lector2));
//        when(departmentService.getDepartmentByName(departmentName)).thenReturn(department);
//        long numberOfEmployees = lectorService.getNumberOfEmployeeForDepartment(departmentName);
//        assertEquals(2, numberOfEmployees);
//    }
//
//    @Test
//    void testGetNumberOfEmployeeForDepartment_NullDepartmentName() {
//        assertThrows(IllegalArgumentException.class, () -> lectorService.getNumberOfEmployeeForDepartment(null));
//    }
//
//    @Test
//    void testGlobalSearchByNameTemplate() {
//        String template = "John";
//        Lector lector1 = new Lector("John Doe", Degree.ASSISTANT, BigDecimal.valueOf(1000));
//        Lector lector2 = new Lector("Jane Smith", Degree.PROFESSOR, BigDecimal.valueOf(2000));
//        when(lectorRepository.findByNameContainingIgnoreCase(template)).thenReturn(Arrays.asList(lector1, lector2));
//        List<Lector> foundLectors = lectorService.globalSearchByNameTemplate(template);
//        assertEquals(2, foundLectors.size());
//        assertTrue(foundLectors.containsAll(Arrays.asList(lector1, lector2)));
//    }
//
//    @Test
//    void testGlobalSearchByNameTemplate_NullTemplate() {
//        assertThrows(IllegalArgumentException.class, () -> lectorService.globalSearchByNameTemplate(null));
//    }
//}
