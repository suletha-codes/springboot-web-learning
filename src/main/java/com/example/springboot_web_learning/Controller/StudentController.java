package com.example.springboot_web_learning.Controller;

import com.example.springboot_web_learning.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student")
    public String getStudent(){

        return studentService.getStudent();
    }
}
