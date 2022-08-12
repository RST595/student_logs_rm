package com.aston.controller;

import com.aston.dto.request.LogItemRequestDTO;
import com.aston.model.LogItem;
import com.aston.service.LogItemService;
import com.aston.dto.response.ResponseMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogItemController {
    private final LogItemService logItemService;

    @PostMapping
    public ResponseMessageDTO addLogItem(@RequestBody LogItemRequestDTO logItem){
        return logItemService.addLogItem(logItem);
    }

    @GetMapping("/{studentId}")
    public List<LogItem> getStudentLogs(@PathVariable int studentId){
        return logItemService.getStudentLogs(studentId);
    }

    @PutMapping("/{logId}")
    public ResponseMessageDTO editLogMessage(@RequestBody LogItemRequestDTO logItem,
                                             @PathVariable int logId){
        return logItemService.editLogMessage(logItem, logId);
    }
}
