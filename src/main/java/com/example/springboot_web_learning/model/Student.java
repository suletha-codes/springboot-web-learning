package com.example.springboot_web_learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.apache.logging.log4j.util.InternalApi;

import javax.annotation.processing.Generated;

@Entity
public class Student {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private int age;
}
