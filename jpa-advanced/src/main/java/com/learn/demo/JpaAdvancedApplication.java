package com.learn.demo;

import com.learn.demo.entity.FullTimeEmployee;
import com.learn.demo.entity.PartTimeEmployee;
import com.learn.demo.repository.CourseRepository;
import com.learn.demo.repository.EmployeeRepository;
import com.learn.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JpaAdvancedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedApplication.class, args);
    }

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
