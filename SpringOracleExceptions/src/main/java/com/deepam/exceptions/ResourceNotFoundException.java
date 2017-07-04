package com.deepam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deepam.exceptions.utils.CustomizedExceptionsList;

/**
 * Exception raised when object is not found
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = CustomizedExceptionsList.EX000TEXT)
public class ResourceNotFoundException extends ServiceException {

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
