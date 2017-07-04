package com.deepam.exceptions;

/**
 * Checked exception thrown when a user is not properly authenticated
 */
public class NotAuthorizedException extends ServiceException {

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
