package com.aston.exception_handler;

import com.aston.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Throwable.class})
    protected ResponseEntity<ResponseMessage> handleException(
            Throwable serviceError) {
        return new ResponseEntity<>(
                new ResponseMessage(false, serviceError.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
