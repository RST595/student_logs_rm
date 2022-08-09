package com.aston.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LogItemRequest {
    private String message;
    private String date;
    private Integer studentId;
}
