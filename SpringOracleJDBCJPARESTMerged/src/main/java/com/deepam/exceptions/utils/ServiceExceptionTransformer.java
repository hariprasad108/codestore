package com.deepam.exceptions.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.deepam.exceptions.AlreadyExistsException;
import com.deepam.exceptions.CustInternalException;
import com.deepam.exceptions.NotAuthorizedException;
import com.deepam.exceptions.CustResourceNotFoundException;
import com.deepam.exceptions.ServiceException;

/**
 * Transforms given exception according to the given http status code.
 */
public final class ServiceExceptionTransformer {
    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionTransformer.class);

    private ServiceExceptionTransformer() {
    }

    /**
     * Transform given Exception e to appropriate exception acceptable by CloudService API according to the provided status code.
     *
     * @param e Transformed Exception.
     * @param statusCode Integer value according to which Exception will be transformed.
     */
    public static ServiceException transform(Exception e, int statusCode) {
        logger.debug("Caught Exception with status code: {}.", statusCode, e);
        ServiceException serviceException;
        switch (HttpStatus.valueOf(statusCode)) {
            case NOT_FOUND:
                serviceException = new CustResourceNotFoundException(e.getMessage(), e);
                break;
            case CONFLICT:
                serviceException = new AlreadyExistsException(e.getMessage(), e);
                break;
            case UNAUTHORIZED:
                serviceException = new NotAuthorizedException(e.getMessage(), e);
                break;
            case INTERNAL_SERVER_ERROR:
                serviceException = new CustInternalException(e.getMessage(), e);
                break;
            default:
                logger.warn("Unsupported Exception status code: {}.", statusCode, e);
                serviceException = new CustInternalException(e.getMessage(), e);
        }
        return serviceException;
    }
}
