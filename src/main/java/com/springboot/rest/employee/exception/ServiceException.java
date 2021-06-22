package com.springboot.rest.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED)
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String message) {
        super(message);
    }

	public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
