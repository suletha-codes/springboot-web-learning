package com.example.springboot_web_learning.repository;

import com.example.springboot_web_learning.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByName(String name);
    List<Student> findByNameContaining(String name);
    Page<Student> findAll(Pageable pageable);
    List<Student> findByAge(int age);
    List<Student> findByAgeGreaterThan(int age);
    List<Student> findByAgeLessThan(int age);
    List<Student> findByAgeGreaterThanEqual(int age);
    List<Student> findByAgeLessThanEqual(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);

}
