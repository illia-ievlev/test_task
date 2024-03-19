package com.ievlev.test_task3.service;

import com.ievlev.test_task3.exceptions.DepartmentWithSpecifiedNameNotFoundException;
import com.ievlev.test_task3.exceptions.HeadOfDepartmentDoesNotExistException;
import com.ievlev.test_task3.model.Department;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public String getNameOfHeadOfDepartment(String departmentName) {
        if(departmentName == null){
            throw new IllegalArgumentException("departmentName can't be null");
        }
        Department department = departmentRepository.findDepartmentByName(departmentName).orElseThrow(() -> {
            throw new DepartmentWithSpecifiedNameNotFoundException("can't find department with name :" + departmentName);
        });
        Lector lector = department.getHeadOfDepartment();
        if (lector == null) {
            throw new HeadOfDepartmentDoesNotExistException("head of department does not exist");
        }
        return lector.getName();
    }

    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findDepartmentByName(departmentName).orElseThrow(() -> {
            throw new DepartmentWithSpecifiedNameNotFoundException("can't find department with name :" + departmentName);
        });
    }

    public List<Lector> getLectorsFromDepartment(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("departmentName can't be null");
        }
        Department department = getDepartmentByName(departmentName);
        return department.getLectorList();
    }


}