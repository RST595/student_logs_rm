package com.aston.service;

import com.aston.dto.StudentRequest;
import com.aston.dto.StudentResponse;
import com.aston.model.Student;
import com.aston.repo.StudentDAO;
import com.aston.util.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDAO studentDAO;
    private final ObjectMapper mapper;

    public StudentResponse getStudentById(Integer id) {
        return mapper.convertValue(studentDAO.getStudentById(id), StudentResponse.class);
    }

    public List<StudentResponse> getAllStudents() {
        return studentDAO.getAllStudents().stream()
                .map(student -> mapper.convertValue(student, StudentResponse.class))
                .collect(Collectors.toList());
    }

    public ResponseMessage addNewStudent(StudentRequest student) {
        Student newStudent = mapper.convertValue(student, Student.class);
        return studentDAO.addNewStudent(newStudent);
    }

    public ResponseMessage deleteStudentById(Integer id) {
        return studentDAO.deleteStudentById(id);
    }
}
