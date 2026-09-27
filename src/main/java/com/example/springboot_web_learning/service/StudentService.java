package com.example.springboot_web_learning.service;

import com.example.springboot_web_learning.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String getStudent(){
        return studentRepository.findStudent();
    }


}
