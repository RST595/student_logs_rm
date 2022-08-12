package com.aston.dto.response;

import com.aston.model.LogItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<LogItem> logItem;
}
