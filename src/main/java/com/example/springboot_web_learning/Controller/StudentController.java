package com.example.springboot_web_learning.Controller;

import com.example.springboot_web_learning.dto.StudentRequestDTO;
import com.example.springboot_web_learning.dto.StudentResponseDTO;
import com.example.springboot_web_learning.dto.StudentUpdateRequestDTO;
import com.example.springboot_web_learning.model.Student;
import com.example.springboot_web_learning.service.StudentService;
import org.springframework.data.domain.Page;
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
//    @GetMapping
//    public ResponseEntity<List<Student>> getAllStudents() {
//        List<Student> students = studentService.getAllStudents();
//        return ResponseEntity.ok(students);
//    }

    // POST - Create a new student
//    @PostMapping
//    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
//        Student savedStudent = studentService.saveStudent(student);
//        return ResponseEntity.ok(savedStudent);
//    }

    // GET - Get one student by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
//
//        try {
//            Student student = studentService.getStudentById(id);
//            return ResponseEntity.ok(student);
//
//        } catch (RuntimeException exception) {
//            return ResponseEntity.notFound().build();
//        }
//    }

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
//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(
//            @PathVariable Long id,
//            @RequestBody Student student) {
//
//        try {
//            Student updatedStudent = studentService.updateStudent(id, student);
//            return ResponseEntity.ok(updatedStudent);
//
//        } catch (RuntimeException exception) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    //DTO

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO studentRequestDTO){

//        Student student = new Student();
//        student.setName(studentRequestDTO.getName());
//        student.setAge(studentRequestDTO.getAge());
//        Student savedStudent = studentService.saveStudent(student);

        Student savedStudent = studentService.saveStudent(studentRequestDTO);
        StudentResponseDTO responseDTO = studentService.convertToResponseDTO(savedStudent);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        List<StudentResponseDTO> students = studentService.getAllStudentResponses();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {

        try {
            StudentResponseDTO responseDTO = studentService.getStudentResponseById(id);
            return ResponseEntity.ok(responseDTO);

        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Student> updateStudent(
//            @PathVariable Long id,
//            @RequestBody StudentUpdateRequestDTO studentUpdateRequestDTO) {
//
//        try {
//            Student updatedStudent = studentService.updateStudent(id, studentUpdateRequestDTO);
//            return ResponseEntity.ok(updatedStudent);
//
//        } catch (RuntimeException exception) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentUpdateRequestDTO studentUpdateRequestDTO) {

        try {
            StudentResponseDTO responseDTO = studentService.updateStudentResponse(id, studentUpdateRequestDTO);
            return ResponseEntity.ok(responseDTO);

        } catch (RuntimeException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    //Custom methods
    //Find by student name in the URL
    @GetMapping("/name/{name}")
    public ResponseEntity<StudentResponseDTO> getStudentByName(@PathVariable String name){
        try {
            StudentResponseDTO responseDTO = studentService.getStudentByName(name);
            return ResponseEntity.ok(responseDTO);
        }catch (RuntimeException exception){
            return ResponseEntity.notFound().build();
        }
    }

    //Search by partial name
    @GetMapping("/search/{name}")
    public ResponseEntity<List<StudentResponseDTO>> searchByStudentName(@PathVariable String name){
        List<StudentResponseDTO> students = studentService.getStudentByNameContaining(name);
        return ResponseEntity.ok(students);
    }




    //Sorting
    @GetMapping("/sort")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudentsSorted(@RequestParam String field){
        List<StudentResponseDTO> students = studentService.getAllStudentsSorted(field);
        return ResponseEntity.ok(students);
    }

    //Sorting by Ascending or descending
    @GetMapping("/sort/dir")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudentsSorted(@RequestParam String field, @RequestParam String direction){
        List<StudentResponseDTO> students = studentService.getAllStudentsSorted(field, direction);
        return ResponseEntity.ok(students);
    }

    //Pagination
    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentWithPagination(@RequestParam int page, @RequestParam int size){
        Page<StudentResponseDTO> students = studentService.getStudentWithPagination(page, size);
        return ResponseEntity.ok(students);
    }

    //Pagination with sorting
    @GetMapping("/page-sort")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentWithPaginationWithSorting(@RequestParam int page,
                                                                                        @RequestParam int size,
                                                                                        @RequestParam String sortBy,
                                                                                        @RequestParam String direction){
        Page<StudentResponseDTO> students = studentService.getStudentWithPaginationWithSorting(page, size, sortBy, direction);
        return ResponseEntity.ok(students);
    }





}

