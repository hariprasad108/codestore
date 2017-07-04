package com.deepam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deepam.exceptions.utils.CustomizedExceptionsList;

/**
 * Checked exception thrown when some unrecoverable error occurs in application
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = CustomizedExceptionsList.EX500TEXT)
public class InternalException extends ServiceException {

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

}
