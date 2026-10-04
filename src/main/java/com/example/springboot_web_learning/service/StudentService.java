package com.example.springboot_web_learning.service;

import com.example.springboot_web_learning.dto.StudentRequestDTO;
import com.example.springboot_web_learning.dto.StudentResponseDTO;
import com.example.springboot_web_learning.model.Student;
import com.example.springboot_web_learning.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public String getStudent(){
//        return studentRepository.findStudent();
//    }

    public Student saveStudent(Student student){

        return studentRepository.save(student);
    }

    //To get list of all students
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }


    //To get a student by specific id from URL
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not Fount!"));
    }

    //To delete a student by id

    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }
    //Update the student by ID
    public Student updateStudent(Long id, Student student){
         Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));

         existingStudent.setName(student.getName());
         existingStudent.setAge(student.getAge());
         return studentRepository.save(existingStudent);
    }

    //DTO
    public Student saveStudent(StudentRequestDTO studentRequestDTO ){
         Student student = new Student();
         student.setName(studentRequestDTO.getName());
         student.setAge(studentRequestDTO.getAge());

         return studentRepository.save(student);
    }

    public StudentResponseDTO convertToResponseDTO(Student student){
        StudentResponseDTO responseDTO = new StudentResponseDTO();

        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());

        return responseDTO;
    }


}
