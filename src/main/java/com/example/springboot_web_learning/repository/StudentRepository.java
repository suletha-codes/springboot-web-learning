package com.example.springboot_web_learning.repository;

import com.example.springboot_web_learning.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{}
