package com.example.springboot_web_learning.Controller;

import com.example.springboot_web_learning.dto.StudentRequestDTO;
import com.example.springboot_web_learning.model.Student;
import com.example.springboot_web_learning.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET - Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // POST - Create a new student
//    @PostMapping
//    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//        Student savedStudent = studentService.saveStudent(student);
//        return ResponseEntity.ok(savedStudent);
//    }

    // GET - Get one student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);

        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Delete student by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Update student by ID
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        try {
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok(updatedStudent);

        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    //DTO

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequestDTO studentRequestDTO){

//        Student student = new Student();
//        student.setName(studentRequestDTO.getName());
//        student.setAge(studentRequestDTO.getAge());
//        Student savedStudent = studentService.saveStudent(student);

        Student savedStudent = studentService.saveStudent(studentRequestDTO);
        return ResponseEntity.ok(savedStudent);
    }
}

