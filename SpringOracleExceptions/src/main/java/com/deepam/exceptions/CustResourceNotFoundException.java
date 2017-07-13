package com.deepam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.deepam.exceptions.utils.CustomizedExceptionsList;

/**
 * Exception raised when object is not found
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = CustomizedExceptionsList.EX000TEXT)
public class CustResourceNotFoundException extends ServiceException {
    private static final long serialVersionUID = 9046657543543925632L;

    public CustResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public CustResourceNotFoundException(String message) {
        super(message);
    }

    public CustResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
