package com.ievlev.test_task3.exceptions;

public class DegreeNotFoundException extends RuntimeException {
    public DegreeNotFoundException() {
    }

    public DegreeNotFoundException(String message) {
        super(message);
    }

    public DegreeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DegreeNotFoundException(Throwable cause) {
        super(cause);
    }
}
