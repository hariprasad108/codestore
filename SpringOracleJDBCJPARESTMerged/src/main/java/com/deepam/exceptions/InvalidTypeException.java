package com.deepam.exceptions;

/**
 * Exception raised when an operation is invoked on an object with wrong type
 */
public class InvalidTypeException extends ServiceException {

    public InvalidTypeException(String msg) {
        super(msg);
    }

    public InvalidTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
