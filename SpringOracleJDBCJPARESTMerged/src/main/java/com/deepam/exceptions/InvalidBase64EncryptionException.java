package com.deepam.exceptions;

/**
 * InvalidBase64EncryptionException exception is thrown when encrypted resource is not valid 
 */
public final class InvalidBase64EncryptionException extends Exception {

    public InvalidBase64EncryptionException(Throwable throwable) {
        super(throwable);
    }

    public InvalidBase64EncryptionException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
