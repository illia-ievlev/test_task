package com.ievlev.test_task3.service;

import com.ievlev.test_task3.model.Department;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.repository.LectorRepository;
import com.ievlev.test_task3.util.CheckInputUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectorService {
    private final LectorRepository lectorRepository;
    private final DepartmentService departmentService;

    public BigDecimal getAverageSalaryOfDepartment(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        Department department = departmentService.getDepartmentWithLectorByName(departmentName);
        List<Lector> lectorList = department.getLectorList();
        BigDecimal totalSalary = lectorList.stream()
                .map(Lector::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalSalary.divide(BigDecimal.valueOf(lectorList.size()));
    }

    public long getNumberOfEmployeeForDepartment(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        Department department = departmentService.getDepartmentWithLectorByName(departmentName);
        return department.getLectorList().size();
    }

    public List<Lector> globalSearchByNameTemplate(String template) {
        if (template == null) {
            throw new IllegalArgumentException("template can't be null");
        }
        return lectorRepository.findByNameContainingIgnoreCase(template);
    }


}
