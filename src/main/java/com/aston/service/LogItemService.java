package com.aston.service;

import com.aston.dto.request.LogItemRequestDTO;
import com.aston.model.LogItem;
import com.aston.model.Student;
import com.aston.repo.LogItemRepo;
import com.aston.repo.StudentRepo;
import com.aston.dto.response.ResponseMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogItemService {
    private final LogItemRepo logItemRepo;
    private final StudentRepo studentRepo;

    public ResponseMessageDTO addLogItem(LogItemRequestDTO logItemRequestDTO) {
        LogItem newLogItem = convertRequestToItem(new LogItem(), logItemRequestDTO);
        Student student = studentRepo.getStudentById(logItemRequestDTO.getStudentId());
        newLogItem.setStudent(student);
        List<LogItem> currentStudentItems = student.getLogItem();
        currentStudentItems.add(newLogItem);
        student.setLogItem(currentStudentItems);
        return studentRepo.updateStudent(student);
    }

    public List<LogItem> getStudentLogs(int studentId) {
        return studentRepo.getStudentById(studentId).getLogItem();
    }

    public ResponseMessageDTO editLogMessage(LogItemRequestDTO request, Integer logId) {
        LogItem currentItem = logItemRepo.getElementById(logId);
        return logItemRepo.updateLogItem(convertRequestToItem(currentItem, request));
    }

    private LogItem convertRequestToItem(LogItem currentItem, LogItemRequestDTO request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentItem.setDate(LocalDate.parse(request.getDate(), formatter));
        currentItem.setMessage(request.getMessage());
        return currentItem;
    }
}
