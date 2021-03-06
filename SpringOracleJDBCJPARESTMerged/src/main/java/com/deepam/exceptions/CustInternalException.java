package com.deepam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deepam.exceptions.utils.CustomizedExceptionsList;

/**
 * Checked exception thrown when some unrecoverable error occurs in application
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = CustomizedExceptionsList.EX500TEXT)
public class CustInternalException extends ServiceException {
    private static final long serialVersionUID = 2932995799051811151L;

    public CustInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustInternalException(String message) {
        super(message);
    }

    public CustInternalException(Throwable cause) {
        super(cause);
    }

}
