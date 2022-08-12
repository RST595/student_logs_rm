package com.aston.exception_handler;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessage {

    BAD_REQUEST("Bad request"),
    STUDENT_NOT_FOUND("Student not found"),
    LOG_ITEM_NOT_FOUND("Log item not found"),
    SERVER_ERROR("Server error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HttpErrorMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
