package com.aston.service;

import com.aston.dto.request.StudentRequestDTO;
import com.aston.dto.response.StudentResponseDTO;
import com.aston.model.Student;
import com.aston.repo.StudentRepo;
import com.aston.dto.response.ResponseMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final ObjectMapper mapper;

    public StudentResponseDTO getStudentById(int id) {
        return mapper.convertValue(studentRepo.getStudentById(id), StudentResponseDTO.class);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepo.getAllStudents().stream()
                .map(student -> mapper.convertValue(student, StudentResponseDTO.class))
                .collect(Collectors.toList());
    }

    public ResponseMessageDTO addNewStudent(StudentRequestDTO student) {
        Student newStudent = mapper.convertValue(student, Student.class);
        return studentRepo.addNewStudent(newStudent);
    }

    public ResponseMessageDTO deleteStudentById(int id) {
        return studentRepo.deleteStudentById(id);
    }
}
