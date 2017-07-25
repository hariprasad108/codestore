package com.deepam.exceptions;


/**
 * Basic Customer Exception
 */
public class ServiceException extends Exception {
    private static final long serialVersionUID = -8712443347767065969L;

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
