package com.aston.service;

import com.aston.dto.LogItemRequest;
import com.aston.model.LogItem;
import com.aston.model.Student;
import com.aston.repo.LogItemDAO;
import com.aston.repo.StudentDAO;
import com.aston.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogItemService {
    private final LogItemDAO logItemDAO;
    private final StudentDAO studentDAO;

    public ResponseMessage addLogItem(LogItemRequest logItemRequest) {
        LogItem newLogItem = convertRequestToItem(new LogItem(), logItemRequest);
        Student student = studentDAO.getStudentById(logItemRequest.getStudentId());
        newLogItem.setStudent(student);
        List<LogItem> currentStudentItems = student.getLogItem();
        currentStudentItems.add(newLogItem);
        student.setLogItem(currentStudentItems);
        return studentDAO.updateStudent(student);
    }

    public List<LogItem> getStudentLogs(int studentId) {
        return studentDAO.getStudentById(studentId).getLogItem();
    }

    public ResponseMessage editLogMessage(LogItemRequest request, Integer logId) {
        LogItem currentItem = logItemDAO.getElementById(logId);
        return logItemDAO.updateLogItem(convertRequestToItem(currentItem, request));
    }

    private LogItem convertRequestToItem(LogItem currentItem, LogItemRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentItem.setDate(LocalDate.parse(request.getDate(), formatter));
        currentItem.setMessage(request.getMessage());
        return currentItem;
    }
}
