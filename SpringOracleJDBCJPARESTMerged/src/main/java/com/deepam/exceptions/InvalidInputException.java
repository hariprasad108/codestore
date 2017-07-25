package com.deepam.exceptions;

/**
 * Exception raised when given inputs are not valid
 */
public class InvalidInputException extends ServiceException {

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
