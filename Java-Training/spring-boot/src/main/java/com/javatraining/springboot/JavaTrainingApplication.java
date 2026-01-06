package com.javatraining.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class JavaTrainingApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaTrainingApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class TrainingController {
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Java Training with Spring Boot!";
    }
    
    @GetMapping("/version")
    public String version() {
        return "{\"version\": \"1.0.0\", \"status\": \"UP\"}";
    }
}
