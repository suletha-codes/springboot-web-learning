package com.example.springboot_web_learning.repository;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    public String findStudent(){
        return "Suletha from repository layer";    }
}
