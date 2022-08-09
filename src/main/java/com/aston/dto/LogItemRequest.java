package com.aston.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LogItemRequest {
    private String message;
    private String date;
    private Integer studentId;
}
