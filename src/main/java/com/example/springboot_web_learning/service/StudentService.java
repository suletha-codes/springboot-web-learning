package com.example.springboot_web_learning.service;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public String getStudent(){
        return "Suletha from the service layer";
    }
}
