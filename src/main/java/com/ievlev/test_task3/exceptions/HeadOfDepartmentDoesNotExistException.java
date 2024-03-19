package com.ievlev.test_task3.exceptions;

public class HeadOfDepartmentDoesNotExistException extends RuntimeException {

    public HeadOfDepartmentDoesNotExistException() {
    }

    public HeadOfDepartmentDoesNotExistException(String message) {
        super(message);
    }

    public HeadOfDepartmentDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public HeadOfDepartmentDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
