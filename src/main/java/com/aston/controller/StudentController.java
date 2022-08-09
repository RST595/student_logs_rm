package com.aston.controller;

import com.aston.dto.StudentRequest;
import com.aston.dto.StudentResponse;
import com.aston.service.StudentService;
import com.aston.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public ResponseMessage addNewStudent(@RequestBody StudentRequest student){
        return studentService.addNewStudent(student);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteStudentById(@PathVariable Integer id){
        return studentService.deleteStudentById(id);
    }
}
