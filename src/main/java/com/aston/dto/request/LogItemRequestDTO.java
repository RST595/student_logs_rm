package com.aston.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LogItemRequestDTO {
    private String message;
    private String date;
    private Integer studentId;
}
