package com.example.springboot_web_learning.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Telling this class contain REST API endpoints
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String Hello(){
        return "My First API from Absolute zero to hero";
    }
}
