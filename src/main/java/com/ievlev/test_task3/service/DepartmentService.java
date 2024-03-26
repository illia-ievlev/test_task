package com.ievlev.test_task3.service;

import com.ievlev.test_task3.exceptions.DepartmentWithSpecifiedNameNotFoundException;
import com.ievlev.test_task3.exceptions.HeadOfDepartmentDoesNotExistException;
import com.ievlev.test_task3.model.Degree;
import com.ievlev.test_task3.model.Department;
import com.ievlev.test_task3.model.Lector;
import com.ievlev.test_task3.repository.DepartmentRepository;
import com.ievlev.test_task3.util.CheckInputUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public String getNameOfHeadOfDepartment(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        Department department = getDepartmentWithHeadOfDepartmentByName(departmentName);
        Lector lector = department.getHeadOfDepartment();
        if (lector == null) {
            throw new HeadOfDepartmentDoesNotExistException("head of department does not exist");
        }
        return lector.getName();
    }

    public Department getDepartmentWithLectorByName(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        return departmentRepository.findDepartmentWithLectorsByName(departmentName)
                .orElseThrow(() -> {
                    throw new DepartmentWithSpecifiedNameNotFoundException("can't find department with name :" + departmentName);
                });
    }

    public Department getDepartmentWithHeadOfDepartmentByName(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        return departmentRepository.findDepartmentWithHeadOfDepartmentByName(departmentName)
                .orElseThrow(() ->
                        new DepartmentWithSpecifiedNameNotFoundException("can't find department with name :" + departmentName)
                );
    }

    public List<Lector> getLectorsFromDepartment(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);

        Department department = getDepartmentWithLectorByName(departmentName);
        return department.getLectorList();
    }

    public Map<String, Long> getDepartmentStatistics(String departmentName) {
        CheckInputUtil.checkInputData(departmentName);
        Map<String, Long> statisticsMap = new HashMap<>();
        List<Lector> lectorList = getLectorsFromDepartment(departmentName);
        long numberOfAssistants = 0;
        long numberOfAssociateProfessors = 0;
        long numberOfProfessors = 0;

        for (Lector lector : lectorList) {
            if (lector.getDegree().equals(Degree.ASSISTANT)) {
                numberOfAssistants++;
            }
            if (lector.getDegree().equals(Degree.PROFESSOR)) {
                numberOfProfessors++;
            }
            if (lector.getDegree().equals(Degree.ASSOCIATE_PROFESSOR)) {
                numberOfAssociateProfessors++;
            }
        }
        statisticsMap.put("assistants", numberOfAssistants);
        statisticsMap.put("associate professors", numberOfAssociateProfessors);
        statisticsMap.put("professors", numberOfProfessors);
        return statisticsMap;
    }


}
