package com.deepam.exceptions;

/**
 * EncryptedResourceException exception is thrown when encrypted resource is not valid 
 */
public class EncryptedResourceException extends ServiceException {
    public EncryptedResourceException(Throwable cause) {
        super(cause);
    }

    public EncryptedResourceException(String message) {
        super(message);
    }

    public EncryptedResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
