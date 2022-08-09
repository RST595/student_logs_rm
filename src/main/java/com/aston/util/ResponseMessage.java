package com.aston.util;

import lombok.Data;

@Data
public class ResponseMessage {
    private final boolean isSuccess;
    private final String message;
}
