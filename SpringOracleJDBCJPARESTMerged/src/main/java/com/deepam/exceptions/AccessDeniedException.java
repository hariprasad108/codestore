package com.deepam.exceptions;

/**
 * Checked exception thrown when user has not appropriate permission
 */
public class AccessDeniedException extends ServiceException {

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

}
