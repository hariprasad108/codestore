package com.deepam.exceptions;

/**
 * Checked exception thrown when the object already exists
 */
public class AlreadyExistsException extends ServiceException {
    public AlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
