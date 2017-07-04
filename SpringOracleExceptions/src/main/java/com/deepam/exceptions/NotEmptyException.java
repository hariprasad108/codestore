package com.deepam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Checked exception thrown when object is not empty
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Empty input expected")
public class NotEmptyException extends ServiceException {
    public NotEmptyException(Throwable cause) {
        super(cause);
    }

    public NotEmptyException(String message) {
        super(message);
    }

    public NotEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
