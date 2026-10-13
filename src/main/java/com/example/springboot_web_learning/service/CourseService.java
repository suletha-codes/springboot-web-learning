package com.example.springboot_web_learning.service;

import com.example.springboot_web_learning.model.Course;
import com.example.springboot_web_learning.repository.CourseRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    //Get all courses
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }


}
