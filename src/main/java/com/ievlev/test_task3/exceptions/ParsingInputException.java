package com.ievlev.test_task3.exceptions;

public class ParsingInputException extends RuntimeException {
    public ParsingInputException() {
    }

    public ParsingInputException(String message) {
        super(message);
    }

    public ParsingInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingInputException(Throwable cause) {
        super(cause);
    }
}
