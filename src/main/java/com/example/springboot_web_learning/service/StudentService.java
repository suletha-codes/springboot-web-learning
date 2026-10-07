package com.example.springboot_web_learning.service;

import com.example.springboot_web_learning.dto.StudentRequestDTO;
import com.example.springboot_web_learning.dto.StudentResponseDTO;
import com.example.springboot_web_learning.dto.StudentUpdateRequestDTO;
import com.example.springboot_web_learning.model.Student;
import com.example.springboot_web_learning.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    public String getStudent(){
//        return studentRepository.findStudent();
//    }

//    public Student saveStudent(Student student){
//
//        return studentRepository.save(student);
//    }
//
//    //To get list of all students
//    public List<Student> getAllStudents(){
//        return studentRepository.findAll();
//    }
//
//
//    //To get a student by specific id from URL
//    public Student getStudentById(Long id) {
//        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not Fount!"));
//    }

    //To delete a student by id
    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentRepository.delete(student);
    }

    //Update the student by ID
//    public Student updateStudent(Long id, Student student){
//         Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));
//
//         existingStudent.setName(student.getName());
//         existingStudent.setAge(student.getAge());
//         return studentRepository.save(existingStudent);
//    }

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

    public List<StudentResponseDTO> getAllStudentResponses(){
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentResponseById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return convertToResponseDTO(student);

    }

    public Student updateStudent(Long id, StudentUpdateRequestDTO studentUpdateRequestDTO){
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));

        existingStudent.setName(studentUpdateRequestDTO.getName());
        existingStudent.setAge(studentUpdateRequestDTO.getAge());
        return studentRepository.save(existingStudent);
    }

    public StudentResponseDTO updateStudentResponse(Long id, StudentUpdateRequestDTO studentUpdateRequestDTO){
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found!"));

        existingStudent.setName(studentUpdateRequestDTO.getName());
        existingStudent.setAge(studentUpdateRequestDTO.getAge());
        Student updatedStudent = studentRepository.save(existingStudent);
        return convertToResponseDTO(updatedStudent);
    }

    //Find by name
    public StudentResponseDTO getStudentByName(String name){
        Student student = studentRepository.findByName(name).orElseThrow(() -> new RuntimeException("Student not found"));

        return convertToResponseDTO(student);
    }

    //Search by partial name
    public List<StudentResponseDTO> getStudentByNameContaining(String name){
        List<Student> students = studentRepository.findByNameContaining(name);

        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //Sorting
    public List<StudentResponseDTO> getAllStudentsSorted(String field){
        Sort sort = Sort.by(field);
        List<Student> students = studentRepository.findAll(sort);

        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //Sorting by ASC/DSC
    public List<StudentResponseDTO> getAllStudentsSorted(String field, String direction){
        Sort sort;
        if(direction.equalsIgnoreCase("desc")){
            sort = Sort.by(field).descending();
        }else {
            sort = Sort.by(field).ascending();
        }
        List<Student> students = studentRepository.findAll(sort);

        return students.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    //Pagination
    public Page<StudentResponseDTO> getStudentWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(this::convertToResponseDTO);
    }

    //Pagination with Sorting
    public Page<StudentResponseDTO> getStudentWithPaginationWithSorting(int page, int size, String sortBy, String direction){
        Sort sort;
        if(direction.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Student> studentPage = studentRepository.findAll(pageable);

        return studentPage.map(this::convertToResponseDTO);
    }






}
