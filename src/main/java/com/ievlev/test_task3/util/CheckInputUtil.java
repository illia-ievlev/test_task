package com.ievlev.test_task3.util;

public class CheckInputUtil {
    public static void checkInputData(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("departmentName can't be null");
        }
        if (departmentName.isBlank()) {
            throw new IllegalArgumentException("department name can't be empty");
        }
    }
}
