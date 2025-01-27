package com.hexaware.exception;

public class InvalidIdException extends Exception {
    private String message;

    public InvalidIdException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
