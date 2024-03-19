package com.ievlev.test_task3.service;

import com.ievlev.test_task3.model.Department;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.repository.LectorRepository;
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
        if (departmentName == null) {
            throw new IllegalArgumentException("departmentName can't be null");
        }
        Department department = departmentService.getDepartmentByName(departmentName);
        List<Lector> lectorList = department.getLectorList();
        BigDecimal totalSalary = BigDecimal.ZERO;
        for (Lector lector : lectorList) {
            totalSalary = totalSalary.add(lector.getSalary());
        }
        return totalSalary.divide(BigDecimal.valueOf(lectorList.size()));
    }

    public long getNumberOfEmployeeForDepartment(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("departmentName can't be null");
        }
        Department department = departmentService.getDepartmentByName(departmentName);
        return department.getLectorList().size();
    }

    public List<Lector> globalSearchByNameTemplate(String template) {
        if (template == null) {
            throw new IllegalArgumentException("template can't be null");
        }
        return lectorRepository.findByNameLike(template);
    }
}
