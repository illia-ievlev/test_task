package com.ievlev.test_task3.exceptions;

public class DepartmentWithSpecifiedNameNotFoundException extends RuntimeException {
    public DepartmentWithSpecifiedNameNotFoundException() {
    }

    public DepartmentWithSpecifiedNameNotFoundException(String message) {
        super(message);
    }

    public DepartmentWithSpecifiedNameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentWithSpecifiedNameNotFoundException(Throwable cause) {
        super(cause);
    }
}
