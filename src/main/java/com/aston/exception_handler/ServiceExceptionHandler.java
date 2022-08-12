package com.aston.exception_handler;

import com.aston.exception.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceError.class})
    protected ResponseEntity<TypicalError> handleException(
            ServiceError serviceError) {
        TypicalError typicalError = new TypicalError(serviceError.getHttpStatus(), serviceError.getHttpError());
        return new ResponseEntity<>(typicalError, typicalError.getStatus());
    }

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<TypicalError> handleGeneralException(
            Throwable throwable) {
        TypicalError typicalError = new TypicalError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.SERVER_ERROR);
        return new ResponseEntity<>(typicalError, typicalError.getStatus());
    }
}
