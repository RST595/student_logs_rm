package com.aston.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessageDTO {
    private final boolean isSuccess;
    private final String message;
}
