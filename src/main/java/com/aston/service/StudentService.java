package com.aston.service;

import com.aston.dto.request.StudentRequestDTO;
import com.aston.dto.response.StudentResponseDTO;
import com.aston.exception.ServiceError;
import com.aston.model.Student;
import com.aston.repo.StudentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.aston.exception_handler.ErrorMessage.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final ObjectMapper mapper;

    public StudentResponseDTO getStudentById(int id) {
        if(id < 0) {throw new ServiceError(HttpStatus.BAD_REQUEST, BAD_REQUEST);}
        return mapper.convertValue(studentRepo.getStudentById(id), StudentResponseDTO.class);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepo.getAllStudents().stream()
                .map(student -> mapper.convertValue(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> addNewStudent(StudentRequestDTO student) {
        Student newStudent = mapper.convertValue(student, Student.class);
        return studentRepo.addNewStudent(newStudent);
    }

    public ResponseEntity<String> deleteStudentById(int id) {
        if(id < 0) {throw new ServiceError(HttpStatus.BAD_REQUEST, BAD_REQUEST);}
        return studentRepo.deleteStudentById(id);
    }
}
