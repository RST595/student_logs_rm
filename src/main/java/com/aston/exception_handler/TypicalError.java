package com.aston.exception_handler;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class TypicalError {

    private HttpStatus status;
    private ErrorMessage message;

    public TypicalError(HttpStatus status, ErrorMessage message) {
        this.status = status;
        this.message = message;
    }
}
