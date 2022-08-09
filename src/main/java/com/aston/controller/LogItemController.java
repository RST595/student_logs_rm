package com.aston.controller;

import com.aston.dto.LogItemRequest;
import com.aston.model.LogItem;
import com.aston.service.LogItemService;
import com.aston.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogItemController {
    private final LogItemService logItemService;

    @PostMapping
    public ResponseMessage addLogItem(@RequestBody LogItemRequest logItem){
        return logItemService.addLogItem(logItem);
    }

    @GetMapping("/{studentId}")
    public List<LogItem> getStudentLogs(@PathVariable Integer studentId){
        return logItemService.getStudentLogs(studentId);
    }

    @PutMapping("/{logId}")
    public ResponseMessage editLogMessage(@RequestBody LogItemRequest logItem,
                                          @PathVariable Integer logId){
        return logItemService.editLogMessage(logItem, logId);
    }
}
