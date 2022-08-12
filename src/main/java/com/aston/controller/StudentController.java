package com.aston.controller;

import com.aston.dto.request.StudentRequestDTO;
import com.aston.dto.response.StudentResponseDTO;
import com.aston.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseEntity<String> addNewStudent(@RequestBody StudentRequestDTO student){
        return studentService.addNewStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int id){
        return studentService.deleteStudentById(id);
    }
}
