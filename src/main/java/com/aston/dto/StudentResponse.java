package com.aston.dto;

import com.aston.model.LogItem;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<LogItem> logItem;
}
