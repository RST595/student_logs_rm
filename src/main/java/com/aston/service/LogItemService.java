package com.aston.service;

import com.aston.dto.request.LogItemRequestDTO;
import com.aston.exception.ServiceError;
import com.aston.model.LogItem;
import com.aston.model.Student;
import com.aston.repo.LogItemRepo;
import com.aston.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.aston.exception_handler.ErrorMessage.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class LogItemService {
    private final LogItemRepo logItemRepo;
    private final StudentRepo studentRepo;

    public ResponseEntity<String> addLogItem(LogItemRequestDTO logItemRequestDTO) {
        LogItem newLogItem = convertRequestToItem(new LogItem(), logItemRequestDTO);
        Student student = studentRepo.getStudentById(logItemRequestDTO.getStudentId());
        newLogItem.setStudent(student);
        List<LogItem> currentStudentItems = student.getLogItem();
        currentStudentItems.add(newLogItem);
        student.setLogItem(currentStudentItems);
        return studentRepo.updateStudentOrLog(student);
    }

    public List<LogItem> getStudentLogs(int studentId) {
        if(studentId < 0) {throw new ServiceError(HttpStatus.BAD_REQUEST, BAD_REQUEST);}
        return studentRepo.getStudentById(studentId).getLogItem();
    }

    public ResponseEntity<String> editLogMessage(LogItemRequestDTO request, int logId) {
        if(logId < 0 || request.getStudentId() < 0) {throw new ServiceError(HttpStatus.BAD_REQUEST, BAD_REQUEST);}
        LogItem currentItem = logItemRepo.getElementById(logId);
        return studentRepo.updateStudentOrLog(convertRequestToItem(currentItem, request));
    }

    private LogItem convertRequestToItem(LogItem currentItem, LogItemRequestDTO request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        currentItem.setDate(LocalDate.parse(request.getDate(), formatter));
        currentItem.setMessage(request.getMessage());
        return currentItem;
    }
}
